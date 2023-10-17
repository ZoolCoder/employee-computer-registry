package com.zoolcoder.employee.computer.registry.service;

import com.zoolcoder.employee.computer.registry.dto.MessageDTO;

public interface NotificationService {

  void sendNotification(MessageDTO message);
}