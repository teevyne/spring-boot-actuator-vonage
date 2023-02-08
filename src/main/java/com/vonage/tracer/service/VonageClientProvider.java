package com.vonage.tracer.service;

import com.vonage.client.VonageClient;

public class VonageClientProvider {

    private static final String APPLICATION_ID = "VONAGE_APPLICATION_ID";
    private static final String PRIVATE_KEY = "VONAGE_PRIVATE_KEY_PATH";
    private static VonageClient instance;

    private VonageClientProvider() {}

    public static VonageClient getInstance() {
        if (instance == null) {
            instance = VonageClient.builder()
                    .applicationId(APPLICATION_ID)
                    .privateKeyPath(PRIVATE_KEY)
                    .build();
        }

        return instance;
    }
}
