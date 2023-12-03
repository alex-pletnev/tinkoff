package edu.hw8.task1;

import edu.hw8.task1.excepions.ServerException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Server {
    private static final int THREAD_POOL_SIZE = 7;
    public static final int PORT = 8083;
    private static final Map<String, String> QUOTES = new ConcurrentHashMap<>();
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Semaphore SEMAPHORE = new Semaphore(THREAD_POOL_SIZE);

    static {
        QUOTES.put(
            "личности",
            "Не переходи на личности там, где их нет"
        );
        QUOTES.put(
            "оскорбления",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами"
        );
        QUOTES.put(
            "глупый",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно..."
                + " Ты просто бог идиотизма.А я тебе говорил, что ты глупый? Так вот,"
                + " я забираю свои слова обратно... Ты просто бог идиотизма."
        );
        QUOTES.put(
            "интеллект",
            "Чем ниже интеллект, тем громче оскорбления"
        );
    }

    private Server() {
    }

    public static void run() throws InterruptedException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            Executor executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
            while (!serverSocket.isClosed()) {
                Socket clientSocket = serverSocket.accept();
                SEMAPHORE.acquire();
                executor.execute(
                    () -> {
                        try (
                            var inputStream = clientSocket.getInputStream();
                            var inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                            var bufferedReader = new BufferedReader(inputStreamReader);

                            var outputStream = clientSocket.getOutputStream();
                            var outputStreamWriter = new OutputStreamWriter(
                                outputStream,
                                StandardCharsets.UTF_8
                            );
                            var responseWriter = new PrintWriter(outputStreamWriter)
                        ) {
                            var request = bufferedReader.readLine();
                            LOGGER.info("S-> {}", request);
                            var splitRequest = request.split(": ");
                            var requestOwner = splitRequest[0];
                            var requestKeyWord = splitRequest[1];
                            var response = "Сервер: " + requestOwner + ", " + QUOTES.getOrDefault(requestKeyWord, "");
                            responseWriter.print(response);
                            responseWriter.flush();
                        } catch (IOException e) {
                            LOGGER.error("Server ", e);
                            throw new ServerException(e);
                        }
                    }
                );
                SEMAPHORE.release();
            }

        } catch (IOException e) {
            LOGGER.error("Server IO ", e);
            throw new ServerException(e);
        }

    }
}
