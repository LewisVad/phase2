package com.phase2.model;

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
@Entity(name="Group")
@Table(schema = "phase2", name = "groups")
public class Group {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public long id;

  @Column(name = "name")
  public String name;
//  @JoinColumn(name = "group_id") // we need to duplicate the physical information
//  private Set<Student> students;

//  @OneToMany

  public long getId() {   return id;  }

  public void setId(long id) {    this.id = id;  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

//  public Set<Student> getStudents() {
//    return students;
//  }
//
//  public void setStudents(Set<Student> students) {
//    this.students = students;
//  }
}