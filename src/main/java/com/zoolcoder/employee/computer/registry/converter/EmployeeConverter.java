package com.zoolcoder.employee.computer.registry.converter;

import com.zoolcoder.employee.computer.registry.dto.ComputerDTO;
import com.zoolcoder.employee.computer.registry.dto.EmployeeDTO;
import com.zoolcoder.employee.computer.registry.model.Employee;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter {

  @Autowired
  private ComputerConverter computerConverter;

  public EmployeeDTO toDto(Employee employee) {
    EmployeeDTO dto = new EmployeeDTO();
    if (employee.getId() != null) {
      dto.setId(employee.getId());
    }

    dto.setEmployeeName(employee.getName());

    // Convert the list of assigned computers to DTOs
    if (employee.getAssignedComputers() != null) {
      List<ComputerDTO> computerDtos = employee.getAssignedComputers().stream()
          .map(computerConverter::toDto).collect(Collectors.toList());

      dto.setAssignedComputers(computerDtos);
    }

    return dto;
  }
}
