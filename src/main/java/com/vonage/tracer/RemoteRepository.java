package com.vonage.tracer;

import com.vonage.tracer.service.MessagingService;
import com.vonage.tracer.service.SmsService;
import com.vonage.tracer.service.VoiceService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RemoteRepository implements HttpTraceRepository {

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

            // call the services to send SMS, Messaging and Voice Messages via Vonage API

            MessagingService.sendSms(
                    "<sender-number-here",
                    "<recipient-number-here",
                    "Check Server - 500 generated. Kindly check the server for an Internal Server Error");

            MessagingService.sendWhatsApp(
                    "<recipient-number-here",
                    "Check Server - 500 generated. Kindly check the server for an Internal Server Error");

            MessagingService.sendViber(
                    "<recipient-number-here",
                    "Check Server - 500 generated. Kindly check the server for an Internal Server Error");

            voiceService.sendVoiceMessage(
                    "<recipient-here",
                    "Check Server - 500 generated. Kindly check the server for an Internal Server Error");

        }
    }
}
