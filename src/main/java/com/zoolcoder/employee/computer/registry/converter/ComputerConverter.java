package com.zoolcoder.employee.computer.registry.converter;

import com.zoolcoder.employee.computer.registry.model.Computer;
import com.zoolcoder.employee.computer.registry.dto.ComputerDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ComputerConverter {

  public ComputerDTO toDto(Computer computer) {
    ComputerDTO computerDto = new ComputerDTO();
    computerDto.setId(computer.getId());
    computerDto.setComputerName(computer.getComputerName());
    computerDto.setMacAddress(computer.getMacAddress());
    computerDto.setIpAddress(computer.getIpAddress());

    if (computer.getAssignedToEmployee() != null) {
      computerDto.setEmployeeId(computer.getAssignedToEmployee().getId());
    }

    return computerDto;
  }

  public Computer toEntity(ComputerDTO computerDto) {
    Computer computer = new Computer();
    computer.setId(computerDto.getId());
    computer.setComputerName(computerDto.getComputerName());
    computer.setMacAddress(computerDto.getMacAddress());
    computer.setIpAddress(computerDto.getIpAddress());

    // You can set the assignedToEmployee if needed based on the employeeId

    return computer;
  }

  public List<ComputerDTO> toDtoList(List<Computer> computers) {
    return computers.stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }
}