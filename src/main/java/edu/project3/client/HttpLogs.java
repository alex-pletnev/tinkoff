package edu.project3.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HttpLogs {

    private static final Logger LOGGER = LogManager.getLogger();

    private HttpResponse<String> getHttpResponse(String url) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();
        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    public List<String> getLogs(String url) throws InterruptedException {
        HttpResponse<String> httpResponse;
        try {
            httpResponse = getHttpResponse(url);
        } catch (IOException e) {
            LOGGER.error("Can't read logs, check your URL: {}", url, e);
            return Collections.emptyList();
        }
        String logs = httpResponse.body();
        var splitLogs = logs.split("\n");
        return Arrays.asList(splitLogs);
    }

}
