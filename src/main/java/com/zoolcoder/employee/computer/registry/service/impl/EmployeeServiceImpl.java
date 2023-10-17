package com.zoolcoder.employee.computer.registry.service.impl;

import com.zoolcoder.employee.computer.registry.converter.EmployeeConverter;
import com.zoolcoder.employee.computer.registry.dto.EmployeeDTO;
import com.zoolcoder.employee.computer.registry.dto.MessageDTO;
import com.zoolcoder.employee.computer.registry.exception.MaxComputerAssignmentException;
import com.zoolcoder.employee.computer.registry.model.Computer;
import com.zoolcoder.employee.computer.registry.model.Employee;
import com.zoolcoder.employee.computer.registry.repository.ComputerRepository;
import com.zoolcoder.employee.computer.registry.repository.EmployeeRepository;
import com.zoolcoder.employee.computer.registry.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private final EmployeeRepository employeeRepository;
  @Autowired
  private final ComputerRepository computerRepository;
  @Autowired
  private final EmployeeConverter employeeConverter;
  @Autowired
  private AdminNotificationService adminNotificationService;

  public EmployeeServiceImpl(EmployeeRepository employeeRepository,
      ComputerRepository computerRepository, EmployeeConverter employeeConverter) {
    this.employeeRepository = employeeRepository;
    this.computerRepository = computerRepository;
    this.employeeConverter = employeeConverter;
  }

  @Override
  public List<EmployeeDTO> getAllEmployees() {
    List<Employee> employees = employeeRepository.findAll();
    return employees.stream().map(employeeConverter::toDto).collect(Collectors.toList());
  }

  @Override
  public Optional<Employee> getEmployeeById(Long id) {
    return employeeRepository.findById(id);
  }

  @Override
  public Employee createEmployee(Employee employee) {
    return employeeRepository.save(employee);
  }

  @Override
  public Employee updateEmployee(Long id, Employee updatedEmployee) {
    Employee existingEmployee = employeeRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

    //TODO: Update fields of existingEmployee using setters from updatedEmployee

    return employeeRepository.save(updatedEmployee);
  }

  @Override
  public void deleteEmployee(Long id) {
    employeeRepository.deleteById(id);
  }

  @Transactional
  @Override
  public void assignComputerToEmployee(Long employeeId, Long computerId) {
    Computer computer = computerRepository.findById(computerId).orElse(null);
    Employee employee = employeeRepository.findById(employeeId).orElse(null);

    if (computer != null && employee != null) {

      if (employee.getAssignedComputers() != null && employee.getAssignedComputers().size() >= 3) {
        adminNotificationService.sendNotification(MessageDTO.builder()
            .level("warning")
            .employeeAbbreviation(employee.getAbbreviation())
            .message("Employee has 3 or more computers assigned.")
            .build());

        throw new MaxComputerAssignmentException(
            "An employee cannot be assigned more than three computers.");
      }

      computer.setAssignedToEmployee(employee);
      computerRepository.save(computer);
    } else {
      throw new IllegalArgumentException("Computer or Employee not found");
    }
  }

  @Override
  @Transactional
  public void unAssignComputerFromEmployee(Long employeeId, Long computerId) {
// Find the employee by ID
    Employee employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EntityNotFoundException("Employee not found with ID: " + employeeId));

    // Find the computer by ID
    Computer computer = computerRepository.findById(computerId)
        .orElseThrow(
            () -> new EntityNotFoundException("Computer not found with ID: " + computerId));

    // Check if the computer is currently assigned to the employee
    if (employee.getAssignedComputers() != null && employee.getAssignedComputers()
        .contains(computer)) {
      // Unassign the computer from the employee
      employee.getAssignedComputers().remove(computer);
      computer.setAssignedToEmployee(null);

      // Save the changes to the database
      employeeRepository.save(employee);
      computerRepository.save(computer);
    }
  }
}
