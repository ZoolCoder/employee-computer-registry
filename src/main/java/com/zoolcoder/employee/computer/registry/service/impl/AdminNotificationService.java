package com.zoolcoder.employee.computer.registry.service.impl;

import com.zoolcoder.employee.computer.registry.dto.MessageDTO;
import com.zoolcoder.employee.computer.registry.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdminNotificationService implements NotificationService {

  @Value("${admin.notification.url}")
  private String adminNotificationUrl;

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public void sendNotification(MessageDTO message) {
    restTemplate.postForObject(adminNotificationUrl, message, Void.class);
  }
}
