package com.vonage.tracer.service;

import com.vonage.client.VonageClient;
import com.vonage.client.messages.MessageResponse;
import com.vonage.client.messages.MessageResponseException;
import com.vonage.client.messages.MessagesClient;
import com.vonage.client.messages.sms.SmsTextRequest;
import com.vonage.client.messages.viber.Category;
import com.vonage.client.messages.viber.ViberTextRequest;
import com.vonage.client.messages.whatsapp.WhatsappTextRequest;
import org.springframework.stereotype.Service;

@Service
public class MessagingService {

    private static final String applicationId = "VONAGE_APPLICATION_ID";
    private static final String privateKey = "VONAGE_PRIVATE_KEY_PATH";

    private static final String VONAGE_NUMBER = "YOUR_VONAGE_NUMBER_HERE";

    public static void sendSms(String toNumber, String text, String s) throws InterruptedException {

        VonageClient client = VonageClient.builder()
                .applicationId(applicationId)
                .privateKeyPath(privateKey)
                .build();

        MessagesClient smsClient = client.getMessagesClient();

        var message = SmsTextRequest.builder()
                .from(VONAGE_NUMBER).to(toNumber)
                .text(text)
                .build();

        try {
            MessageResponse response = smsClient.sendMessage(message);
            System.out.println("Message sent successfully. ID: "+response.getMessageUuid());
        }
        catch (MessageResponseException mrx) {
            switch (mrx.getStatusCode()) {
                default: throw mrx;
                case 401: // Bad credentials
                    throw new IllegalStateException(mrx.getTitle(), mrx);
                case 422: // Invalid
                    throw new IllegalStateException(mrx.getDetail(), mrx);
                case 402: // Low balance
                    client.getAccountClient().topUp("transactionID");
                    break;
                case 429: // Rate limit
                    Thread.sleep(12_000);
                    break;
            }
        }
    }

    public static void sendWhatsApp(String toNumber, String text) throws InterruptedException {

        VonageClient client = VonageClient.builder()
                .applicationId(applicationId)
                .privateKeyPath(privateKey)
                .build();

        MessagesClient whatsAppClient = client.getMessagesClient();

        var message = WhatsappTextRequest.builder()
                .from(VONAGE_NUMBER).to(toNumber)
                .text(text)
                .build();
        try {
            MessageResponse response = whatsAppClient.sendMessage(message);
            System.out.println("Message sent successfully. ID: "+response.getMessageUuid());
        }
        catch (MessageResponseException mrx) {
            switch (mrx.getStatusCode()) {
                default: throw mrx;
                case 401: // Bad credentials
                    throw new IllegalStateException(mrx.getTitle(), mrx);
                case 422: // Invalid
                    throw new IllegalStateException(mrx.getDetail(), mrx);
                case 402: // Low balance
                    client.getAccountClient().topUp("transactionID");
                    break;
                case 429: // Rate limit
                    Thread.sleep(12_000);
                    break;
            }
        }
    }

    public static void sendViber(String toNumber, String text) throws InterruptedException {

        VonageClient client = VonageClient.builder()
                .applicationId(applicationId)
                .privateKeyPath(privateKey)
                .build();

        MessagesClient viberClient = client.getMessagesClient();

        var message = ViberTextRequest.builder()
                .from(VONAGE_NUMBER).to(toNumber)
                .text(text)
                .category(Category.TRANSACTION)
                .build();
        try {
            MessageResponse response = viberClient.sendMessage(message);
            System.out.println("Message sent successfully. ID: "+response.getMessageUuid());
        }
        catch (MessageResponseException mrx) {
            switch (mrx.getStatusCode()) {
                default: throw mrx;
                case 401: // Bad credentials
                    throw new IllegalStateException(mrx.getTitle(), mrx);
                case 422: // Invalid
                    throw new IllegalStateException(mrx.getDetail(), mrx);
                case 402: // Low balance
                    client.getAccountClient().topUp("transactionID");
                    break;
                case 429: // Rate limit
                    Thread.sleep(12_000);
                    break;
            }
        }
    }
}