package com.phase2.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DiscriminatorValue(value = "c")
@Entity(name="Course")
//@PrimaryKeyJoinColumn(name = "person_id")
@Table(schema = "phase2", name = "courses")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique=true, nullable = false)
  private long id;

  @Column(name = "title")
  public String title;

//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "teacher_id")
//  private Teacher teacher;

  public long getId() {    return id;  }

  public void setId(long id) {    this.id = id;  }

  public String getTitle() {    return title;  }

  public void setTitle(String title) {    this.title = title;  }
//
//  public Teacher getTeacher() {    return teacher;  }
//
//  public void setTeacher(Teacher teacher) {    this.teacher = teacher;  }
}
