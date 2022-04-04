package com.example.application.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import elemental.json.JsonArray;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@RestController
public class TwitterController {
    @Value("${BEARER_TOKEN}")
    private String bearerToken;
    private static HttpURLConnection connection;

    @GetMapping("/tweets")
    public String tweets() throws Exception {
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();

        try {
            URL url = new URL("https://api.twitter.com/2/tweets/search/recent?query=najib&expansions=author_id&tweet.fields=id,created_at,text,author_id&user.fields=id,created_at,name,username,profile_image_url");

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

        return parse(responseContent.toString());
    }

    public static String parse(String responseBody) {
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray data = jsonObject.getJSONArray("data");
        for (int i = 0; i < data.length(); i++) {
            JSONObject tweet = data.getJSONObject(i);
            System.out.println(tweet.get("text"));
        }

        return "done";
    }


}
