package com.vonage.tracer.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;

@Service
public class SmsService {


    private static final String API_URL = "https://rest.nexmo.com/sms/json";
    private static final String API_KEY = "YOUR_API_KEY";
    private static final String API_SECRET = "YOUR_API_SECRET";

    public void sendSms(String from, String to, String text) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(API_URL);
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Authorization", "Basic " + Base64
                .getEncoder()
                .encodeToString((API_KEY + ":" + API_SECRET).getBytes()));

        StringEntity requestBody = new StringEntity("{"
                + "\"from\":\"" + from + "\","
                + "\"to\":\"" + to + "\","
                + "\"text\":\"" + text + "\""
                + "}");
        request.setEntity(requestBody);

        HttpResponse response = httpClient.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode == 200) {
            HttpEntity responseEntity = response.getEntity();
//            String responseString = Entity.toString(responseEntity, "UTF-8");
//            System.out.println("SMS sent successfully: " + responseString);
        } else {
            System.out.println("Error sending SMS: " + statusCode);
        }
    }
}
