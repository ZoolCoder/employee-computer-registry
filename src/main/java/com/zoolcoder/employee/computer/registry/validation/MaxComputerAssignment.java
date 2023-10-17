package com.zoolcoder.employee.computer.registry.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MaxComputerAssignmentValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxComputerAssignment {
  String message() default "An employee cannot be assigned more than three computers";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}