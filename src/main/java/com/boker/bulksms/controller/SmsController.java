package com.boker.bulksms.controller;

import com.boker.bulksms.dto.SmsRequest;
import com.boker.bulksms.service.SmsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {
    @Autowired
    private SmsServices smsServices;
    @PostMapping("/send-sms")
    public ResponseEntity<String> sendSms(@RequestBody  SmsRequest smsRequest){
        return  ResponseEntity.ok(smsServices.sendSMS(smsRequest));
    }
}
