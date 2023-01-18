package com.vonage.tracer;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class RemoteRepository implements HttpTraceRepository {


    @Override
    public List<HttpTrace> findAll() {
        return Collections.emptyList();
    }

    @Override
    public void add(HttpTrace trace) {

        int responseStatusCode = trace.getResponse().getStatus();

        if (responseStatusCode == 500) {
            // call the services to send Email, SMS and Voice Messages via Vonage API
        }


    }
}
