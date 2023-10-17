package com.zoolcoder.employee.computer.registry.model;

import com.zoolcoder.employee.computer.registry.validation.MaxComputerAssignment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@MaxComputerAssignment
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee extends BaseEntity {

  @Column(nullable = false)
  String name;
  @Column(nullable = false, unique = true)
  @Size(min = 3, max = 3, message = "Abbreviation must be exactly 3 letters")
  String abbreviation;
  @OneToMany(mappedBy = "assignedToEmployee")
  private List<Computer> assignedComputers;
}