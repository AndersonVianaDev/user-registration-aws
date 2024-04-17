package com.anderson.msuser.config.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsAsyncClient;

import java.net.URI;

@Configuration
public class SnsConfiguration {

    @Value("${url-aws}")
    private String url;

    @Bean
    public SnsAsyncClient snsAsyncClient() {
        return SnsAsyncClient.builder()
                .endpointOverride(URI.create(url))
                .region(Region.US_EAST_1)
                .build();
    }
}
