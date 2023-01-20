package com.vonage.tracer;

import com.vonage.tracer.service.EmailService;
import com.vonage.tracer.service.MessagingService;
import com.vonage.tracer.service.SmsService;
import com.vonage.tracer.service.VoiceService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
//@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class RemoteRepository implements HttpTraceRepository {

    @Autowired
    private EmailService emailService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private MessagingService messagingService;

    @Autowired
    private VoiceService voiceService;

    @Override
    public List<HttpTrace> findAll() {
        return Collections.emptyList();
    }

    @SneakyThrows
    @Override
    public void add(HttpTrace trace) {

        int responseStatusCode = trace.getResponse().getStatus();

        if (responseStatusCode == 500) {

            // call the services to send Email, SMS, Messaging and Voice Messages via Vonage API

            emailService.sendEmail(
                    "<your-email-here>",
                    "recipient-mail-here",
                    "Check Server - 500 generated",
                    "Kindly check the server for an Internal Server Error");

            smsService.sendSms(
                    "<sender-number-here",
                    "<recipient-number-here",
                    "Check Server - 500 generated. Kindly check the server for an Internal Server Error");

            messagingService.sendWhatsApp(
                    "<recipient-number-here",
                    "Check Server - 500 generated. Kindly check the server for an Internal Server Error");

            voiceService.sendVoiceMessage(
                    "<recipient-here",
                    "Check Server - 500 generated. Kindly check the server for an Internal Server Error");

        }
    }
}
