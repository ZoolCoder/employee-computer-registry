package com.zoolcoder.employee.computer.registry.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.zoolcoder.employee.computer.registry.converter.ComputerConverter;
import com.zoolcoder.employee.computer.registry.dto.ComputerDTO;
import com.zoolcoder.employee.computer.registry.model.Computer;
import com.zoolcoder.employee.computer.registry.repository.ComputerRepository;
import com.zoolcoder.employee.computer.registry.service.impl.ComputerServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ComputerServiceImplTest {

  @Mock
  private ComputerRepository computerRepository;

  @Mock
  private ComputerConverter computerConverter;

  //@InjectMocks
  private ComputerServiceImpl computerService;

  @BeforeEach
  void setUp() {
    computerService = new ComputerServiceImpl(computerRepository, computerConverter);
  }

  @Test
  void shouldGetAllComputers() {
    List<Computer> computers = List.of(new Computer(), new Computer());
    when(computerRepository.findAll()).thenReturn(computers);

    List<ComputerDTO> computerDTOS = computerService.getAllComputers();

    assertEquals(2, computerDTOS.size());
  }

  @Test
  void shouldGetComputerById() {
    Computer computer = new Computer();
    Long id = 1L;
    when(computerRepository.findById(id)).thenReturn(Optional.of(computer));

    Optional<Computer> computerOptional = computerService.getComputerById(id);

    assertEquals(computer, computerOptional.get());
  }

  @Test
  void shouldAddComputer() {
    Computer computer = new Computer();
    when(computerRepository.save(computer)).thenReturn(computer);

    Computer savedComputer = computerService.addComputer(computer);

    assertEquals(computer, savedComputer);
  }

  @Test
  void shouldUpdateComputer() {
    Long id = 1L;
    Computer updatedComputer = new Computer();
    Computer existingComputer = new Computer();
    when(computerRepository.findById(id)).thenReturn(Optional.of(existingComputer));

    computerService.updateComputer(id, updatedComputer);

    // Assert: Add assertions or verifications as needed
    // For example, verify that existingComputer is updated correctly
    verify(computerRepository).save(existingComputer);
  }

  @Test
  void shouldDeleteComputer() {
    Long id = 1L;

    computerService.deleteComputer(id);

    // Assert: Verify that the deleteComputer method behaves as expected
    verify(computerRepository).deleteById(id);
  }
}