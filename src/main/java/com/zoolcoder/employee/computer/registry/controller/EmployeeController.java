package com.zoolcoder.employee.computer.registry.controller;

import com.zoolcoder.employee.computer.registry.dto.EmployeeDTO;
import com.zoolcoder.employee.computer.registry.exception.MaxComputerAssignmentException;
import com.zoolcoder.employee.computer.registry.model.Computer;
import com.zoolcoder.employee.computer.registry.model.Employee;
import com.zoolcoder.employee.computer.registry.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/employee")
@Tag(name = "employee", description = "employee management APIs")
@RestController
public class EmployeeController {

  public static final String CLASS_NAME = EmployeeController.class.getName();

  @Autowired
  private EmployeeService employeeService;

  @Operation(summary = "Retrieve employees", description = "Get all employees object.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Successful retrieval of employees", content = @Content(array = @ArraySchema(schema = @Schema(implementation = EmployeeDTO.class)))),
      @ApiResponse(responseCode = "404", description = "employee not found.", content = {
          @Content(schema = @Schema())})})
  @GetMapping("/getAll")
  public List<EmployeeDTO> getAllEmployees() {
    return employeeService.getAllEmployees();
  }

  @Operation(summary = "Retrieve employee", description = "Get employee object by specifying its id.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "employee details for given id", content = @Content(array = @ArraySchema(schema = @Schema(implementation = EmployeeDTO.class)))),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = {
          @Content(schema = @Schema())}),
      @ApiResponse(responseCode = "404", description = "employee not found.", content = {
          @Content(schema = @Schema())})})
  @GetMapping("/{id}")
  public Optional<Employee> getEmployeeById(@PathVariable Long id) {
    return employeeService.getEmployeeById(id);
  }

  @Operation(summary = "Add a new employee", description = "This api is used to add new employee")
  @ApiResponse(responseCode = "201", description = "employee successfully added", content = {
      @Content(schema = @Schema(implementation = Computer.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})
  @PostMapping("/add")
  public Employee createEmployee(@RequestBody Employee employee) {
    return employeeService.createEmployee(employee);
  }

  @Operation(summary = "Update the employee details", description = "Update employee details by providing the id.")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "employee details are successfully updated", content = @Content(array = @ArraySchema(schema = @Schema(implementation = EmployeeDTO.class)))),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = {
          @Content(schema = @Schema())}),
      @ApiResponse(responseCode = "404", description = "employee not found for the given id", content = {
          @Content(schema = @Schema())})})
  @PutMapping("/{id}")
  public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
    return employeeService.updateEmployee(id, updatedEmployee);
  }

  @Operation(summary = "Delete employee details", description = "Delete employee object by using its id.")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "employee details are successfully deleted from the DB", content = @Content(array = @ArraySchema(schema = @Schema(implementation = EmployeeDTO.class)))),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = {
          @Content(schema = @Schema())}),
      @ApiResponse(responseCode = "404", description = "employee not found for the given id", content = {
          @Content(schema = @Schema())})})
  @DeleteMapping("/{id}")
  public void deleteEmployee(@PathVariable Long id) {
    employeeService.deleteEmployee(id);
  }

  @PostMapping("/{employeeId}/assign-computer/{computerId}")
  public ResponseEntity<?> assignComputerToEmployee(@PathVariable Long employeeId,
      @PathVariable Long computerId) {
    try {
      employeeService.assignComputerToEmployee(employeeId, computerId);
      return ResponseEntity.ok("Computer assigned successfully.");
    } catch (MaxComputerAssignmentException e) {
      return ResponseEntity.badRequest()
          .body("An employee cannot be assigned more than three computers.");
    }
  }

  @PostMapping("/{employeeId}/unAssign-computer/{computerId}")
  public ResponseEntity<?> unAssignComputerToEmployee(@PathVariable Long employeeId,
      @PathVariable Long computerId) {
    try {
      employeeService.unAssignComputerFromEmployee(employeeId, computerId);
      return ResponseEntity.ok("Computer unAssigned successfully.");
    } catch (MaxComputerAssignmentException e) {
      return ResponseEntity.badRequest()
          .body("An employee cannot be unAssigned.");
    }
  }
}
