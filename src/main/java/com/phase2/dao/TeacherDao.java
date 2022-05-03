package com.phase2.dao;

import com.phase2.model.Teacher;

import java.util.List;

/**
 * DAO class to manage {@link Teacher}
 */
public interface TeacherDao {

  void init();

  /**
   * Method to find all students
   *
   * @return a list of {@link Teacher}s
   */
  List<Teacher> findAll();

  /**
   *
   * @param id - {@link Teacher#id}
   * @return
   */
  Teacher getTeacherById(long id);

  /**
   * Method to create student
   *
   * @return a student with {@link Teacher}
   */
//  void createStudent(Student student);
  /**
   * Method to delete checked students
   *
   */
  void delete(long id);

  /**
   * Method to create student
   *
   * @return new {@link Teacher#id}
   */
  Teacher save(Teacher teacher);

//  List<Long> teachersWithCourses();

//  /**
//   * Method to edit student
//   *
//   */
//  Student update(Student student);
//  void save(Student student);
//  List<Student> findStudent();

//  List<Student> findStudent(String fullName, String address, char gender, LocalDate startDate, LocalDate endDate, long groupId);
}
