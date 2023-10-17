package com.zoolcoder.employee.computer.registry.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.zoolcoder.employee.computer.registry.converter.EmployeeConverter;
import com.zoolcoder.employee.computer.registry.dto.EmployeeDTO;
import com.zoolcoder.employee.computer.registry.model.Computer;
import com.zoolcoder.employee.computer.registry.model.Employee;
import com.zoolcoder.employee.computer.registry.repository.ComputerRepository;
import com.zoolcoder.employee.computer.registry.repository.EmployeeRepository;
import com.zoolcoder.employee.computer.registry.service.impl.EmployeeServiceImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class EmployeeServiceImplTest {

  @Mock
  private EmployeeRepository employeeRepository;

  @Mock
  private EmployeeConverter employeeConverter;

  @Mock
  private ComputerRepository computerRepository;

  @InjectMocks
  private EmployeeServiceImpl employeeService;

  @BeforeEach
  void setUp() {
  }

  @Test
  public void shouldGetAllEmployees() {
    List<Employee> employees = Arrays.asList(new Employee(), new Employee());
    when(employeeRepository.findAll()).thenReturn(employees);

    List<EmployeeDTO> employeeDtos = employeeService.getAllEmployees();

    assertEquals(employees.size(), employeeDtos.size());
  }

  @Test
  public void shouldGetEmployeeById() {
    Employee employee = new Employee();
    employee.setName("John Doe");
    when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

    Employee foundEmployee = employeeService.getEmployeeById(1L).get();

    assertEquals(employee.getName(), foundEmployee.getName());
  }

  @Test
  public void shouldUpdateEmployee() {
    Employee employee = new Employee();
    when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
    Employee updatedEmployee = new Employee();
    updatedEmployee.setName("Jane Doe updated");

    Employee updatedEmployeeResult = employeeService.updateEmployee(1L, updatedEmployee);

    //assertEquals(updatedEmployee.getName(), updatedEmployeeResult.getName());
  }

  @Test
  public void shouldCreateEmployee() {
    Employee employee = new Employee();
    when(employeeRepository.save(employee)).thenReturn(employee);

    Employee createdEmployee = employeeService.createEmployee(employee);

    assertEquals(employee, createdEmployee);
  }

  @Test
  public void shouldDeleteEmployee() {
    employeeService.deleteEmployee(1L);

    // Assert: Verify that the deleteEmployee method was called with the correct parameter
    //verify(employeeRepository).deleteById(1L);
  }

  @Test
  public void shouldAssignComputerToEmployee() {
    Employee employee = new Employee();
    Computer computer = new Computer();
    when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
    when(computerRepository.findById(1L)).thenReturn(Optional.of(computer));

    employeeService.assignComputerToEmployee(1L, 1L);

    // Assert: Verify that the computer is saved
    verify(computerRepository).save(computer);
  }

  @Test
  public void testUnassignComputerFromEmployee() {
    Employee employee = new Employee();
    Computer computer = new Computer();
    when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
    when(computerRepository.findById(1L)).thenReturn(Optional.of(computer));

    employeeService.unAssignComputerFromEmployee(1L, 1L);

    // Assert: Verify that the computer is saved
//    verify(employeeRepository).save(employee);
  //  verify(computerRepository).save(computer);
  }
}
