package com.zoolcoder.employee.computer.registry.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDTO {
  private String level;
  private String employeeAbbreviation;
  private String message;
}