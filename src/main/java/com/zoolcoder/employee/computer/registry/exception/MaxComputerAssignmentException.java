package com.zoolcoder.employee.computer.registry.exception;

public class MaxComputerAssignmentException extends RuntimeException {

  public MaxComputerAssignmentException() {
    super();
  }

  public MaxComputerAssignmentException(String message) {
    super(message);
  }

  public MaxComputerAssignmentException(String message, Throwable cause) {
    super(message, cause);
  }

  public MaxComputerAssignmentException(Throwable cause) {
    super(cause);
  }
}