package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HackerNews {

    private HackerNews() {
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public static long[] hackerNewsTopStories() throws InterruptedException {
        long[] data = new long[] {};
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
                .build();
            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            data = parseLongArrayFromJsonString(response.body());

        } catch (IOException e) {
            LOGGER.error("IOException in hackerNewsTopStories()", e);
        } catch (NumberFormatException e) {
            LOGGER.error("Response contains invalid data (long array required)", e);
        }
        LOGGER.info("Found {} id", data.length);
        Arrays.stream(data).forEach(LOGGER::info);
        return data;

    }

    public static String news(long id) throws InterruptedException {
        StringBuilder uri = new StringBuilder("https://hacker-news.firebaseio.com/v0/item/")
            .append(id)
            .append(".json");
        Pattern outerPattern = Pattern.compile("\"title\":\"(.+?)\"");
        String newsName = null;
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(uri.toString()))
                .build();
            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            var matcher = outerPattern.matcher(response.body());
            matcher.find();
            newsName = matcher.group(1);
        } catch (IOException e) {
            LOGGER.error("IOException in news(long id)", e);
        } catch (IllegalStateException e) {
            LOGGER.info("No news found with id {}", id);
            return null;
        }
        LOGGER.info("Id {} brought up an article with the title '{}'", id, newsName);
        return newsName;

    }

    private static long[] parseLongArrayFromJsonString(String jsonString) {
        var substr = jsonString.substring(1, jsonString.length() - 1);
        var strLongs = substr.split(",");
        long[] longs = new long[strLongs.length];
        for (int i = 0; i < strLongs.length; i++) {
            longs[i] = Long.parseLong(strLongs[i]);
        }
        return longs;
    }

}
