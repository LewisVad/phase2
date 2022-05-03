package com.phase2.dao;

import com.phase2.model.Course;
import com.phase2.model.Group;

import java.util.List;

/**
 * DAO class to manage {@link Group}
 */
public interface CourseDao {

  void init();

  /**
   * Method to find all groups
   *
   * @return a list of {@link Group}s
   */
  List<Course> getAllCourse();

  /**
   *
   * @param id - {@link Group#id}
   * @return
   */
  Course findById(long id);

  /**
   * Method to delete group
   *
   * @return list with remaining group, without {@link com.phase2.model.Student}s
   * @param id
   */
  void deleteCourse(long id);

  /**
   * Method to edit group
   *
   * @return edited {@link Group#id}
   */
  Course saveCourse(Course course);

  /**
   * Method to get groups ids with students
   *
   * @return ids of {@link Group#id}
   */
//  List<Long> teachersWithCourses();
}
