package com.zoolcoder.employee.computer.registry.controller;

import com.zoolcoder.employee.computer.registry.dto.ComputerDTO;
import com.zoolcoder.employee.computer.registry.model.Computer;
import com.zoolcoder.employee.computer.registry.service.ComputerService;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/computer")
@Tag(name = "Computer", description = "computer management APIs")
@RestController
public class ComputerController {

  public static final String CLASS_NAME = ComputerController.class.getName();

  @Autowired
  private ComputerService computerService;

  @Operation(summary = "Retrieve computers", description = "Get all computers object.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Successful retrieval of computers", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ComputerDTO.class)))),
      @ApiResponse(responseCode = "404", description = "computer not found.", content = {
          @Content(schema = @Schema())})})
  @GetMapping("/getAll")
  public List<ComputerDTO> getAllComputers() {
    return computerService.getAllComputers();
  }

  @Operation(summary = "Retrieve computer", description = "Get computer object by specifying its id.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "computer details for given id", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ComputerDTO.class)))),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = {
          @Content(schema = @Schema())}),
      @ApiResponse(responseCode = "404", description = "computer not found.", content = {
          @Content(schema = @Schema())})})
  @GetMapping("/{id}")
  public Optional<Computer> getComputerById(@PathVariable Long id) {
    return computerService.getComputerById(id);
  }

  @Operation(summary = "Add a new computer", description = "This api is used to add new computer")
  @ApiResponse(responseCode = "201", description = "computer successfully added", content = {
      @Content(schema = @Schema(implementation = Computer.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})
  @PostMapping("/add")
  public Computer createComputer(@RequestBody Computer computer) {
    return computerService.addComputer(computer);
  }

  @Operation(summary = "Update the computer details", description = "Update computer details by providing the id.")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "computer details are successfully updated", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ComputerDTO.class)))),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = {
          @Content(schema = @Schema())}),
      @ApiResponse(responseCode = "404", description = "computer not found for the given id", content = {
          @Content(schema = @Schema())})})
  @PutMapping("/{id}")
  public Computer updateComputer(@PathVariable Long id, @RequestBody Computer updatedComputer) {
    return computerService.updateComputer(id, updatedComputer);
  }

  @Operation(summary = "Delete computer details", description = "Delete computer object by using its id.")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "computer details are successfully deleted from the DB", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ComputerDTO.class)))),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = {
          @Content(schema = @Schema())}),
      @ApiResponse(responseCode = "404", description = "computer not found for the given id", content = {
          @Content(schema = @Schema())})})
  @DeleteMapping("/{id}")
  public void deleteComputer(@PathVariable Long id) {
    computerService.deleteComputer(id);
  }
}

