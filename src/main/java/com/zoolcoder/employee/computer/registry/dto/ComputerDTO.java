package com.zoolcoder.employee.computer.registry.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComputerDTO {
  private Long id;
  private String computerName;
  private String ipAddress;
  private String macAddress;
  private Long employeeId;
}
