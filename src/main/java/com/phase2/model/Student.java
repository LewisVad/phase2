package com.phase2.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DiscriminatorValue(value = "s")
@Entity(name="Student")
@PrimaryKeyJoinColumn(name = "person_id")
@Table(schema = "phase2", name = "students")
public class Student extends Person {

  @ManyToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name="group_id")
  private Group group;

  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "library_membership_id",referencedColumnName = "id")
  private Membership membership;

  public Student(long id, String firstName, String lastName, char gender, String picture, String email, LocalDate dateOfBirth, List<Phone> phones, Address address) {
    super(id, firstName, lastName, gender, picture, email, dateOfBirth, phones, address);
  }

  public Student() {
    super();
  };

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

  public Membership getMembership() {return membership;}

  public void setMembership(Membership membership) {this.membership = membership;}

//  @Override
//  public int hashCode() {    return getClass().hashCode();  }
//
//  @Override
//  public boolean equals(Object obj) {    return super.equals(obj);  }
}
