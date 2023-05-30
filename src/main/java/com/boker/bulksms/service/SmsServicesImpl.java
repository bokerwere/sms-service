package com.boker.bulksms.service;

import com.africastalking.AfricasTalking;
import com.africastalking.SmsService;
import com.boker.bulksms.Configuration.AfricaTalkingConfiguration;
import com.boker.bulksms.dto.SmsRequest;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SmsServicesImpl implements SmsServices {
    @Autowired
    private AfricaTalkingConfiguration africaTalkingConfiguration;
    @SneakyThrows
    @Override
    public String sendSMS(SmsRequest smsRequest) {
        // Initialize
        String username = "sandbox";    // use 'sandbox' for development in the test environment
        String apiKey = "51e86482a70c5698cfb5c6faed723d2fbc966d60e07f3cb602f1a88632d89567";       // use your sandbox app API key for development in the test environment
        AfricasTalking.initialize(africaTalkingConfiguration.getUserName(), africaTalkingConfiguration.getApiKey());
        // Initialize a service e.g. SMS
        SmsService sms =AfricasTalking.getService(AfricasTalking.SERVICE_SMS);
        // Use the service
         sms.send(smsRequest.getMessage(), "boker-1", new String[]{String.format("%s",smsRequest.getPhoneNumbers())},true);
        return "success";
    }
}