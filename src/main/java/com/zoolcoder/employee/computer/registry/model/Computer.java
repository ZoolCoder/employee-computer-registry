package com.zoolcoder.employee.computer.registry.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Computer extends BaseEntity {

  @Column(nullable = false, unique = true)
  private String macAddress;
  @Column(nullable = false)
  private String computerName;
  @Column(nullable = false)
  private String ipAddress;
  private String description;
  @ManyToOne // Many computers can be assigned to one employee
  @JoinColumn(name = "employee_id")
  private Employee assignedToEmployee;
}