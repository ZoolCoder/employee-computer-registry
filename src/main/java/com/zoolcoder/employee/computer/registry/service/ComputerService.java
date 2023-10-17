package com.zoolcoder.employee.computer.registry.service;

import com.zoolcoder.employee.computer.registry.dto.ComputerDTO;
import com.zoolcoder.employee.computer.registry.model.Computer;
import java.util.List;
import java.util.Optional;

public interface ComputerService {

  List<ComputerDTO> getAllComputers();

  Optional<Computer> getComputerById(Long id);

  Computer addComputer(Computer computer);

  Computer updateComputer(Long id, Computer computer);

  void deleteComputer(Long id);
}