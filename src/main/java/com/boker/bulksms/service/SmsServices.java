package com.boker.bulksms.service;

import com.boker.bulksms.dto.SmsRequest;

public interface SmsServices {
    String sendSMS(SmsRequest smsRequest);
}
