package com.phase2.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DiscriminatorValue(value = "t")
@Entity(name="Teacher")
@PrimaryKeyJoinColumn(name = "person_id")
@Table(schema = "phase2", name = "teachers")
public class Teacher extends Person {

  @Column(name = "salary")
  private double salary;

//  @OneToMany(mappedBy = "teacher",cascade = {CascadeType.ALL})
//  private List<Course> course = new Arraylist()<>;

  public Teacher(long id, String firstName, String lastName, char gender, String picture, String email, LocalDate dateOfBirth, List<Phone> phones, Address address) {
    super(id, firstName, lastName, gender, picture, email, dateOfBirth, phones, address);
  }

  public Teacher() {
    super();
  };

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

//  public List<Course> getCourse() {    return course;  }
//
//  public void setCourse(List<Course> course) {    this.course = course;  }

}
