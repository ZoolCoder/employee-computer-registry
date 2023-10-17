package com.zoolcoder.employee.computer.registry.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.zoolcoder.employee.computer.registry.dto.ComputerDTO;
import com.zoolcoder.employee.computer.registry.model.Computer;
import com.zoolcoder.employee.computer.registry.model.Employee;
import com.zoolcoder.employee.computer.registry.service.EmployeeService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComputerResolver implements GraphQLResolver<Computer> {

  private final EmployeeService employeeService;

  @Autowired
  public ComputerResolver(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  public Optional<Employee> assignedToEmployee(ComputerDTO computerDTO) {
    if (computerDTO.getEmployeeId() != null) {
      return employeeService.getEmployeeById(computerDTO.getEmployeeId());
    }
    return null;
  }
}
