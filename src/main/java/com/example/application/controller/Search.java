package com.example.application.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Search {
    @Value("${BEARER_TOKEN}")
    private String bearerToken;
    private static HttpURLConnection connection;

    public void searchTweet(String searchParam) throws Exception {
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();

        try {
            URL url = new URL("https://api.twitter.com/2/tweets/search/recent?query="+ searchParam +"&expansions=author_id&tweet.fields=id,created_at,text,author_id&user.fields=id,created_at,name,username,profile_image_url");

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + bearerToken);
            connection.setRequestProperty("Content-Type", "application/json");

            //Request setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            //Test if the response from server is successful
            int responseCode = connection.getResponseCode();

            if (responseCode >= 300) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }   else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            System.out.println("Response code: " + responseCode);
//            System.out.println("Response content: " + responseContent.toString());
        }   catch (MalformedURLException e) {
            e.printStackTrace();
        }   catch (IOException e) {
            e.printStackTrace();
        }   finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public static String parse(String responseBody) {
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray data = jsonObject.getJSONArray("data");
        for (int i = 0; i < data.length(); i++) {
            JSONObject tweet = data.getJSONObject(i);
            System.out.println(tweet.get("text"));
        }

//        System.out.println(jsonObject.getJSONObject("includes").getJSONArray("users");
//                .getJSONObject(0).get("name"));
        JSONArray users = jsonObject.getJSONObject("includes").getJSONArray("users");
        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            System.out.println(user.get("name"));
            System.out.println(user.get("profile_image_url"));
        }

        return "done";
    }


}
