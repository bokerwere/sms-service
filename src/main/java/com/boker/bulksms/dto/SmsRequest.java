package com.boker.bulksms.dto;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
@Data
public class SmsRequest {
    private String message;
    private String phoneNumbers;

}
