package com.japcdev.userapp.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AppListener extends AppService{

  @KafkaListener(topics = "app-topic", groupId = "japcdev")
  public void listen(String message) {
    logger.info("Received message in group foo: " +  message);
  }

}

