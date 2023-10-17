package com.zoolcoder.employee.computer.registry.dto;

import java.util.List;
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
public class EmployeeDTO {
  private Long id;
  private String employeeName;
  private List<ComputerDTO> assignedComputers;
}