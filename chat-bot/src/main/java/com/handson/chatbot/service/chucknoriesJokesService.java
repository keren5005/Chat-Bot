package com.handson.chatbot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service // Marks this class as a Spring service for autowiring
public class chucknoriesJokesService {

    @Autowired
    private ObjectMapper objectMapper;

    private final OkHttpClient client = new OkHttpClient();

    /**
     * Fetches a list of jokes based on the given keyword.
     *
     * @param keyword The search term for jokes.
     * @return A list of jokes or an empty list if none found.
     */
    public List<JokeObject> getJokes(String keyword) {
        String url = "https://api.chucknorris.io/jokes/search?query=" + keyword;
        Request request = new Request.Builder().url(url).get().build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.err.println("Failed to fetch jokes: " + response);
                return Collections.emptyList();
            }

            String res = response.body().string();
            JokeResponse jokeResponse = objectMapper.readValue(res, JokeResponse.class);
            return jokeResponse.getResult() != null ? jokeResponse.getResult() : Collections.emptyList();
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Fetches a single joke based on the given keyword.
     *
     * @param keyword The search term for the joke.
     * @return A single joke or a default message if none found.
     */
    public String getJoke(String keyword) {
        List<JokeObject> jokes = getJokes(keyword);
        if (jokes.isEmpty()) {
            return "No jokes found for the keyword: " + keyword;
        }
        return jokes.get(0).getValue();
    }

    // Inner class to represent the response structure from the Chuck Norris API
    public static class JokeResponse {
        private List<JokeObject> result;

        public List<JokeObject> getResult() {
            return result;
        }

        public void setResult(List<JokeObject> result) {
            this.result = result;
        }
    }

    // Inner class to represent each joke object
    public static class JokeObject {
        private String id;
        private String value;
        private List<String> categories;
        private String iconUrl;
        private String createdAt;
        private String updatedAt;
        private String url;

        public JokeObject() {}

        // Getters and setters for each field
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getValue() { return value; }
        public void setValue(String value) { this.value = value; }

        public List<String> getCategories() { return categories; }
        public void setCategories(List<String> categories) { this.categories = categories; }

        public String getIconUrl() { return iconUrl; }
        public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }

        public String getCreatedAt() { return createdAt; }
        public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

        public String getUpdatedAt() { return updatedAt; }
        public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }

        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
    }
}
