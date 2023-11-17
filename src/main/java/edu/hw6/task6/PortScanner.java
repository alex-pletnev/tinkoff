package edu.hw6.task6;

import java.io.File;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PortScanner {
    private static final int START_PORT = 1;

    private static final int END_PORT = 65535;
    private static final int PORT_TXT_LINE_MAX_LENGTH = 4;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String TCP = "TCP";
    private static final String UDP = "UDP";

    private static final Map<Integer, String> TSP_PORTS = new HashMap<>();
    private static final Map<Integer, String> UDP_PORTS = new HashMap<>();

    private static final Set<PortInfo> PORT_INFO_SET = new TreeSet<>();

    static {
        initWellKnownTcpAndUdpPorts();
    }

    private PortScanner() {
    }

    public static void printPortsInfo() {
        var linePattern = "{}\t{}\t{}";
        scanTcpPorts();
        scanUdpPorts();
        LOGGER.info(linePattern, "Протокол", "Порт", "Сервис");
        for (var portInfo : PORT_INFO_SET) {
            LOGGER.info(linePattern, portInfo.protocol(), portInfo.port(), portInfo.description());
        }
    }

    private static void scanTcpPorts() {
        for (int port = START_PORT; port <= END_PORT; port++) {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                serverSocket.close();
            } catch (IOException e) {
                PORT_INFO_SET.add(new PortInfo(port, TCP, TSP_PORTS.getOrDefault(port, "")));
            }
        }
    }

    private static void scanUdpPorts() {
        for (int port = START_PORT; port <= END_PORT; port++) {
            try {
                DatagramSocket datagramSocket = new DatagramSocket(port);
                datagramSocket.close();
            } catch (IOException e) {
                PORT_INFO_SET.add(new PortInfo(port, UDP, UDP_PORTS.getOrDefault(port, "")));
            }
        }
    }

    private static void initWellKnownTcpAndUdpPorts() {
        try (Scanner scanner = new Scanner(new File("src/main/resources/Ports.txt"))) {
            while (scanner.hasNext()) {
                var line = scanner.nextLine();
                var splitLine = line.split(":");
                if (splitLine.length == PORT_TXT_LINE_MAX_LENGTH) {
                    TSP_PORTS.put(Integer.parseInt(splitLine[0]), splitLine[PORT_TXT_LINE_MAX_LENGTH - 1]);
                    UDP_PORTS.put(Integer.parseInt(splitLine[0]), splitLine[PORT_TXT_LINE_MAX_LENGTH - 1]);
                } else {
                    switch (splitLine[1]) {
                        case (TCP) -> TSP_PORTS.put(Integer.parseInt(splitLine[0]), splitLine[splitLine.length - 1]);
                        case (UDP) -> UDP_PORTS.put(Integer.parseInt(splitLine[0]), splitLine[splitLine.length - 1]);
                        default -> {
                        }
                    }
                }
            }
        } catch (Exception e) {

            LOGGER.error("Problem with initWellKnownTcpAndUdpPorts", e);
        }
    }
}
