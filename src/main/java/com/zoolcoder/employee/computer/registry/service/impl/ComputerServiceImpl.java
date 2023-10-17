package com.zoolcoder.employee.computer.registry.service.impl;

import com.zoolcoder.employee.computer.registry.converter.ComputerConverter;
import com.zoolcoder.employee.computer.registry.dto.ComputerDTO;
import com.zoolcoder.employee.computer.registry.model.Computer;
import com.zoolcoder.employee.computer.registry.repository.ComputerRepository;
import com.zoolcoder.employee.computer.registry.service.ComputerService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComputerServiceImpl implements ComputerService {

  @Autowired
  private final ComputerRepository computerRepository;
  @Autowired
  private final ComputerConverter computerConverter;

  public ComputerServiceImpl(ComputerRepository computerRepository,
      ComputerConverter computerConverter) {
    this.computerRepository = computerRepository;
    this.computerConverter = computerConverter;
  }

  @Transactional(readOnly = true)
  @Override
  public List<ComputerDTO> getAllComputers() {
    List<Computer> computers = computerRepository.findAll();
    return computers.stream().map(computerConverter::toDto).collect(Collectors.toList());
  }

  @Override
  public Optional<Computer> getComputerById(Long id) {
    return computerRepository.findById(id);
  }


  @Override
  public Computer addComputer(Computer computer) {
    return computerRepository.save(computer);
  }

  @Override
  public Computer updateComputer(Long id, Computer updatedComputer) {
    Computer existingComputer = computerRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Computer not found"));

    //TODO: Update fields of existingComputer using setters from updatedComputer

    return computerRepository.save(updatedComputer);
  }

  @Override
  public void deleteComputer(Long id) {
    computerRepository.deleteById(id);
  }
}
