package com.phase2.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class StudentSearchForm {

  private String fullName;
  private String fullAddress;
  private char gender;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate startDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate endDate;
  private Group group;

  public StudentSearchForm(String fullName, String fullAddress, char gender, LocalDate startDate, LocalDate endDate, Group group) {
    this.fullName = fullName;
    this.fullAddress = fullAddress;
    this.gender = gender;
    this.startDate = startDate;
    this.endDate = endDate;
    this.group = group;
  }

  public StudentSearchForm() {  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getFullAddress() {    return fullAddress;  }

  public void setFullAddress(String fullAddress) {
    this.fullAddress = fullAddress;
  }

  public char getGender() {
    return gender;
  }

  public void setGender(char gender) {
    this.gender = gender;
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

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }


}
