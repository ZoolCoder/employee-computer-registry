package com.zoolcoder.employee.computer.registry.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.zoolcoder.employee.computer.registry.dto.ComputerDTO;
import com.zoolcoder.employee.computer.registry.dto.EmployeeDTO;
import com.zoolcoder.employee.computer.registry.model.Employee;
import com.zoolcoder.employee.computer.registry.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeResolver implements GraphQLResolver<Employee> {

  private final ComputerService computerService;

  @Autowired
  public EmployeeResolver(ComputerService computerService) {
    this.computerService = computerService;
  }

  public List<ComputerDTO> assignedComputers(EmployeeDTO employeeDTO) {
    if (employeeDTO.getId() != null) {
      return null; //computerService.getComputerById(employeeDTO.getId());
    }
    return null;
  }
}