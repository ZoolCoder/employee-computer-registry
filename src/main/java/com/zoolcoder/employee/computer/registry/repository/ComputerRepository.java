package com.zoolcoder.employee.computer.registry.repository;

import com.zoolcoder.employee.computer.registry.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {
}
