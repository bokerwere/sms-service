package com.boker.bulksms.Configuration;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class AfricaTalkingConfiguration {
    @Value("${sms.userName}")
    private String userName;
    @Value("${sms.api_key}")
    private String apiKey;
    @Value("${sms.base_url}")
    private String baseUrl;
}
