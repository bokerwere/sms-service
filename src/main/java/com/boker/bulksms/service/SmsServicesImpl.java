package com.boker.bulksms.service;

import com.africastalking.AfricasTalking;
import com.africastalking.SmsService;
import com.boker.bulksms.Configuration.AfricaTalkingConfiguration;
import com.boker.bulksms.dto.SMSRequestDto;
import com.boker.bulksms.dto.SmsRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
@Slf4j
public class SmsServicesImpl implements SmsServices {
    @Autowired
    private AfricaTalkingConfiguration africaTalkingConfiguration;
    private  RestTemplate restTemplate=new RestTemplate();
    private ObjectMapper mapper=new ObjectMapper();
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


    @Override
    public void sendSms() {
      try {
          SMSRequestDto smsRequestDto=new SMSRequestDto();
          smsRequestDto.setUsername(africaTalkingConfiguration.getUserName());
          smsRequestDto.setMessage("sms with springboot");
          smsRequestDto.setEnqueue(1);
          smsRequestDto.setFrom("boker-1");
          smsRequestDto.setTo(Arrays.asList("+254718356681"));
          HttpHeaders headers = new HttpHeaders();
          headers.addIfAbsent("apiKey",africaTalkingConfiguration.getApiKey());
          headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
          MultiValueMap<String,Object>map=new LinkedMultiValueMap<>();
          for (String receivedPhone:smsRequestDto.getTo()){
              map.set("to",receivedPhone);
          }
          map.set("username",smsRequestDto.getUsername());
          map.set("from",smsRequestDto.getFrom());
          map.set("message",smsRequestDto.getMessage());
          map.set("enqueue",1);
          HttpEntity<MultiValueMap<String,Object>> httpEntity=new HttpEntity<>(map,headers);
          String baseUrl= africaTalkingConfiguration.getBaseUrl();
          UriComponents build= UriComponentsBuilder.fromHttpUrl(baseUrl).build();
          ResponseEntity<JsonNode> response=restTemplate.exchange(build.toString(), HttpMethod.POST,httpEntity,JsonNode.class);
          log.info("response{}",response);

      }catch (RestClientException exception){
          log.error("message{}",exception.getMessage());
      }

    }


}