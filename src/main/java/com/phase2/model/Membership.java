package com.phase2.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Membership")
@Table(schema = "phase2", name = "library_memberships")
public class Membership {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public long id;


  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name = "start_date")
  public LocalDate startDate;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name = "end_date")
  public LocalDate endDate;

  @Column(name = "status")
  public String status;

  public Membership(long id, String status, LocalDate startDate, LocalDate endDate) {
    this.id = id;
    this.status = status;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public Membership(String status, LocalDate startDate, LocalDate endDate) {
    this.status = status;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public Membership() {
  }

  public Long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
