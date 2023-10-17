package com.zoolcoder.employee.computer.registry.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity implements Serializable {

  private static final long serialVersionUID = -7643472199261145774L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @CreatedDate
  @Column(name = "date_created", nullable = true, updatable = false)
  private OffsetDateTime logDateCreated;

  @LastModifiedDate
  @Column(name = "last_updated", nullable = true, updatable = true)
  private OffsetDateTime logLastUpdated;

}
