package edu.hw8.task1;

import edu.hw8.task1.excepions.ClientException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client extends Thread {
    private static final Logger LOGGER = LogManager.getLogger();

    public Client(String name, String response, String ip, int port) {

        super(() -> {
            try (
                Socket clientSocket = new Socket(ip, port);
                var outputStream = clientSocket.getOutputStream();
                var outputStreamWriter = new OutputStreamWriter(
                    outputStream,
                    StandardCharsets.UTF_8
                );
                var requestWriter = new PrintWriter(outputStreamWriter);

                var inputStream = clientSocket.getInputStream();
                var inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                var bufferedReader = new BufferedReader(inputStreamReader)
            ) {
                requestWriter.print(name + ": " + response);
                requestWriter.flush();

                LOGGER.info("C -> {}", bufferedReader.readLine());

            } catch (IOException e) {
                LOGGER.error("Client", e);
                throw new ClientException(e);
            }
        });
    }
}
