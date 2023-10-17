package com.zoolcoder.employee.computer.registry.validation;

import com.zoolcoder.employee.computer.registry.model.Employee;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MaxComputerAssignmentValidator implements ConstraintValidator<MaxComputerAssignment, Employee> {

  @Override
  public boolean isValid(Employee employee, ConstraintValidatorContext context) {
    if (employee == null || employee.getAssignedComputers() == null) {
      return true; // No computers assigned, so it's valid
    }

    // Check if the number of assigned computers is less than or equal to 3
    return employee.getAssignedComputers().size() <= 3;
  }
}
