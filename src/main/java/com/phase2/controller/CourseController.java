package com.phase2.controller;

import com.phase2.dao.CourseDao;
import com.phase2.model.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

@Controller
public class CourseController {

  @Autowired
  private CourseDao courseDao;

  public CourseController(CourseDao courseDao) {
    this.courseDao = courseDao;
  }

  @GetMapping("/courses")
  public String listCourses(Model model) {
    List<Course> courses = courseDao.getAllCourse();
//    List<Long> contain = courseDao.teachersWithCourses();
    model.addAttribute("courses", courses);

    return "course-list";
  }

  @GetMapping("/course/{id}")
  public String editCourse(@PathVariable long id, Model model) {
    if (id != 0) {
      Course course = courseDao.findById(id);
      model.addAttribute("course", course);
    } else {
      Course course = new Course();
      model.addAttribute("course", course);
    }
    return "course-edit";
  }

  @RequestMapping(value = "/newCourse", method = RequestMethod.GET)
  public String createGroup(Model model) {
    Course course = new Course();
    model.addAttribute("course", course);
    return "course-edit";
  }

  @Valid
  @RequestMapping(value = "/saveCourse", method = RequestMethod.POST)
  public ModelAndView submitCourse(@ModelAttribute Course courseForm) throws IOException {
    Course course = courseForm.getId() == 0 ? new Course() : courseDao.findById(courseForm.getId());
    String courseTitle = courseForm.getTitle();
    if (!("".equals(courseTitle))) {
      course.setTitle(courseTitle);
//      if(courseForm.getTeacher() == null) {course.setTeacher(null);}
      courseDao.saveCourse(course);
    }

    return new ModelAndView("redirect:/course/"+course.getId());
  }

  @PostMapping("/deleteCourse/{id}")
  public String deleteCourse(@PathVariable long id) {
//    List<Long> contain = courseDao.teachersWithCourses();

//    if (contain.contains(id)) {
      courseDao.deleteCourse(id);
      return "redirect:/courses";
//    } else {
//      return "delete-course";
//    }
  }
}
