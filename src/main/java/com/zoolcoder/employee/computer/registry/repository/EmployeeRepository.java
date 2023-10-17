package com.zoolcoder.employee.computer.registry.repository;

import com.zoolcoder.employee.computer.registry.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
