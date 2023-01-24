package com.vonage.tracer.service;

import org.springframework.stereotype.Service;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Base64;

@Service
public class MessagingService {

    private static final String API_URL = "https://messages-sandbox.nexmo.com/v0.1/messages";
    private static final String API_KEY = "YOUR_API_KEY";
    private static final String API_SECRET = "YOUR_API_SECRET";

    public void sendWhatsApp(String to, String text) throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(API_URL);
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((API_KEY + ":" + API_SECRET).getBytes()));

        JSONObject requestBody = new JSONObject();
        requestBody.put("to", to);
        requestBody.put("channel", "whatsapp");
        requestBody.put("messages", new JSONObject().put("content", new JSONObject().put("type", "text").put("text", text)));

        StringEntity requestEntity = new StringEntity(requestBody.toString());
        request.setEntity(requestEntity);

        HttpResponse response = httpClient.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode == 200) {
            HttpEntity responseEntity = response.getEntity();
//            String responseString = Entity.toString(responseEntity, "UTF-8");
//            System.out.println("WhatsApp message sent successfully: " + responseString);
        } else {
            System.out.println("Error sending WhatsApp message: " + statusCode);
        }
    }

    public void sendFacebook(String to, String text) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(API_URL);
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((API_KEY + ":" + API_SECRET).getBytes()));

        JSONObject requestBody = new JSONObject();
        requestBody.put("to", to);
        requestBody.put("channel", "facebook");
        requestBody.put("messages", new JSONObject().put("content", new JSONObject().put("type", "text").put("text", text)));

        StringEntity requestEntity = new StringEntity(requestBody.toString());
        request.setEntity(requestEntity);

        HttpResponse response = httpClient.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode == 200) {
            HttpEntity responseEntity = response.getEntity();
//            String responseString = Entity.toString(responseEntity, "UTF-8");
//            System.out.println("Facebook message sent successfully: " + responseString);
        } else {
            System.out.println("Error sending Facebook message: " + statusCode);
        }
    }

    public void sendViber(String to, String text) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(API_URL);
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((API_KEY + ":" + API_SECRET).getBytes()));

        JSONObject requestBody = new JSONObject();
        requestBody.put("to", to);
        requestBody.put("channel", "viber");
        requestBody.put("messages", new JSONObject().put("content", new JSONObject().put("type", "text").put("text", text)));

        StringEntity requestEntity = new StringEntity(requestBody.toString());
        request.setEntity(requestEntity);

        HttpResponse response = httpClient.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode == 200) {
            HttpEntity responseEntity = response.getEntity();
//            String responseString = Entity.toString(responseEntity, "UTF-8");
//            System.out.println("Viber message sent successfully: " + responseString);
        } else {
            System.out.println("Error sending Viber message: " + statusCode);
        }
    }
}