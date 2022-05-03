package com.phase2.dao;

import com.phase2.model.Student;
import com.phase2.model.StudentSearchForm;

import java.util.List;

/**
 * DAO class to manage {@link Student}
 */
public interface StudentDao {

  void init();

  /**
   * Method to find all students
   *
   * @return a list of {@link Student}s
   */
  List<Student> findAll();

  /**
   *
   * @param id - {@link Student#id}
   * @return
   */
  Student getStudentById(long id);

  /**
   * Method to create student
   *
   * @return a student with {@link Student}
   */
//  void createStudent(Student student);
  /**
   * Method to delete checked students
   *
   */
  void deleteStudent(long id);
//  void deleteStudent(Student student);

  /**
   * Method to create student
   *
   * @return new {@link Student#id}
   */
  Student save(Student student);

//  /**
//   * Method to edit student
//   *
//   */
//  Student update(Student student);
//  void save(Student student);
//  List<Student> findStudent();

  List<Student> findStudent(StudentSearchForm studentSearchForm);

//  void setGroupToNull(long id);
}
