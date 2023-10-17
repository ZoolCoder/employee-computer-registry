package com.zoolcoder.employee.computer.registry.service;

import com.zoolcoder.employee.computer.registry.dto.EmployeeDTO;
import com.zoolcoder.employee.computer.registry.model.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

  List<EmployeeDTO> getAllEmployees();

  Optional<Employee> getEmployeeById(Long id);

  Employee createEmployee(Employee employee);

  Employee updateEmployee(Long id, Employee updatedEmployee);

  void deleteEmployee(Long id);

  void assignComputerToEmployee(Long employeeId, Long computerId);

  void unAssignComputerFromEmployee(Long employeeId, Long computerId);
}
