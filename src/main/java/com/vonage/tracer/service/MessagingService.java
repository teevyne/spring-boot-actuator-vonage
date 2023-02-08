package com.vonage.tracer.service;

import com.vonage.client.VonageClient;
import com.vonage.client.messages.MessageResponse;
import com.vonage.client.messages.MessagesClient;
import com.vonage.client.messages.sms.SmsTextRequest;
import com.vonage.client.messages.viber.Category;
import com.vonage.client.messages.viber.ViberTextRequest;
import com.vonage.client.messages.whatsapp.WhatsappTextRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessagingService {

    private static final String VONAGE_NUMBER = "YOUR_VONAGE_NUMBER_HERE";

    public static void sendSms(String toNumber, String text) {

        VonageClient client = VonageClientProvider.getInstance();

        MessagesClient smsClient = client.getMessagesClient();

        var message = SmsTextRequest.builder()
                .from(VONAGE_NUMBER).to(toNumber)
                .text(text)
                .build();


            MessageResponse response = smsClient.sendMessage(message);
            log.info("Message sent successfully. ID: "+response.getMessageUuid());

    }

    public static void sendWhatsApp(String toNumber, String text) {

        VonageClient client = VonageClientProvider.getInstance();

        MessagesClient whatsAppClient = client.getMessagesClient();

        var message = WhatsappTextRequest.builder()
                .from(VONAGE_NUMBER).to(toNumber)
                .text(text)
                .build();

            MessageResponse response = whatsAppClient.sendMessage(message);
            log.info("Message sent successfully. ID: "+response.getMessageUuid());
    }

    public static void sendViber(String toNumber, String text) {

        VonageClient client = VonageClientProvider.getInstance();

        MessagesClient viberClient = client.getMessagesClient();

        var message = ViberTextRequest.builder()
                .from(VONAGE_NUMBER).to(toNumber)
                .text(text)
                .category(Category.TRANSACTION)
                .build();

            MessageResponse response = viberClient.sendMessage(message);
            log.info("Message sent successfully. ID: "+response.getMessageUuid());
    }
}