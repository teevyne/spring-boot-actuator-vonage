package com.vonage.tracer;

import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.autoconfigure.trace.http.HttpTraceProperties;
import org.springframework.boot.actuate.autoconfigure.trace.http.HttpTraceAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "management.trace.http", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(HttpTraceProperties.class)
@AutoConfigureBefore(HttpTraceAutoConfiguration.class)
public class ActuatorConfig {

    @Bean
    @ConditionalOnMissingBean(HttpTraceRepository.class)
    public RemoteRepository traceRepository() {
        return new RemoteRepository();
    }
}