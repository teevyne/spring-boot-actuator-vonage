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
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class VoiceService {

    private static final String API_URL = "https://api.nexmo.com/v0.1/calls";
    private static final String API_KEY = "YOUR_API_KEY";
    private static final String API_SECRET = "YOUR_API_SECRET";

    public static void sendVoiceMessage(String to, String message) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(API_URL);
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((API_KEY + ":" + API_SECRET).getBytes(StandardCharsets.UTF_8)));

        JSONObject requestBody = new JSONObject();
        requestBody.put("to", new JSONObject().put("type", "phone").put("number", to));
        requestBody.put("voice_callback_event_url", "https://example.com/webhooks/events");
        requestBody.put("voice_callback_url", "https://example.com/webhooks/answer");
        requestBody.put("answer_url", "https://nexmo-community.github.io/ncco-examples/text_to_speech.json");

        StringEntity entity = new StringEntity(requestBody.toString());
        request.setEntity(entity);

        HttpResponse response = httpClient.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode == 200) {
            HttpEntity responseEntity = response.getEntity();
//            String responseString = Entity.toString(responseEntity, "UTF-8");
//            System.out.println("Voice message sent successfully: " + responseString);
        } else {
            System.out.println("Error sending voice message: " + statusCode);
        }
    }
}
