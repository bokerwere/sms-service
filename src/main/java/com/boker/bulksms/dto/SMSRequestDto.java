package com.boker.bulksms.dto;

import lombok.Data;

import java.util.List;
@Data
public class SMSRequestDto {
    private String username;
    private String message;
    private  String from ;
    private List<String>to;
    private  int enqueue;
}
