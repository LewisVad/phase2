package com.phase2.controller;

//import com.phase2.dao.CourseDao;
import com.phase2.dao.PhoneDao;
import com.phase2.dao.PhoneTypesDao;
import com.phase2.dao.TeacherDao;
import com.phase2.model.Phone;
import com.phase2.model.PhoneType;
import com.phase2.model.Teacher;
import com.phase2.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Controller
public class TeacherController {

  private TeacherService teacherService;
  @Autowired
  private TeacherController teacherController;
  @Resource
  private TeacherDao teacherDao;
//  @Resource
//  private CourseDao courseDao;
  @Resource
  private PhoneDao phoneDao;
  @Resource
  private PhoneTypesDao phoneTypesDao;

  public TeacherController(TeacherDao teacherDao, TeacherService teacherService) {
        this.teacherDao = teacherDao;
        this.teacherService = teacherService;
  }

  @PostConstruct
  public void setUp() {
    teacherDao.init();
  }

  @GetMapping("/teachers")
  public String listTeachers(Model model) {
    List<Teacher> teachers = teacherDao.findAll();
    model.addAttribute("teachers", teachers);
    return "teacher-list";
  }

  @GetMapping("/editTeacher/{id}")
  public String loadTeacherById(@PathVariable long id, Model model) {
    model.addAttribute("teacher", teacherDao.getTeacherById(id));

//    Map<Long, String> courseItems = new HashMap<>();
//    for (Course course : courseDao.getAllCourse()) {
//      courseItems.put(course.getId(), course.getTitle());
//    }
//    model.addAttribute("courseItems", courseItems);

    List<Phone> phones = phoneDao.getAllPhones();
    model.addAttribute("phones", phones);

    List<PhoneType> phoneTypes = phoneTypesDao.getAllPhoneTypes();
    model.addAttribute("phoneTypes", phoneTypes);

    return "teacher-edit";
  }

  @RequestMapping(value = "/saveTeacher", method = RequestMethod.POST)
  public ModelAndView submitTeacher(@ModelAttribute("teacher") Teacher teacher) {

    return teacherService.saveTeacher(teacher);
  }

//  @RequestMapping(value = "/deleteTeacher", method = RequestMethod.POST)
//  public String deleteTeacher(@PathVariable long id) {
//    List<Long> contain = teacherDao.teachersWithCourses();
//
//    if (contain.contains(id)) {
//      teacherDao.delete(id);
//      return "redirect:/teachers";
//    } else {
//      return "delete-teacher";
//    }
//  }

  @RequestMapping(value = "/newTeacher", method = RequestMethod.GET)
  public String createTeacher(Model model) {
    Teacher teacher = new Teacher();
    model.addAttribute("teacher", teacher);

//    Map<Long, String> courseItems = new HashMap<>();
//    for (Course course : courseDao.getAllCourse()) {
//      courseItems.put(course.getTeacher().getId(), course.getTitle());
//    }
//    model.addAttribute("courseItems", courseItems);

    Map<Integer, String> phoneTypeItems = new HashMap<>();
    for(PhoneType phoneType : phoneTypesDao.getAllPhoneTypes()) {
      phoneTypeItems.put(phoneType.getId(), phoneType.getName());
    }

    return "teacher-edit";
  }

  @PostMapping("/deleteTeacher/{id}")
  public String deleteTeacher(@PathVariable long id) {

      teacherDao.delete(id);
      return "redirect:/teachers";
  }

  @RequestMapping(value = "/save/teacherImage/{id}", method = RequestMethod.POST)
  public String saveImage(@RequestParam("picture") MultipartFile picture, @PathVariable long id) throws IOException {

    Teacher teacher = teacherDao.getTeacherById(id);
    String fileName = StringUtils.cleanPath(picture.getOriginalFilename());
    if (picture.getSize() <= 0) {
      teacher.setPicture(null);

    } else {
      try {
        byte[] bytes = picture.getBytes();
        String base64Picture = Base64.getEncoder().encodeToString(bytes);
        teacher.setPicture(base64Picture);
      } catch (Exception throwables) {
        throwables.printStackTrace();
      }
    }
    teacherDao.save(teacher);
    return "redirect:/editTeacher/" + teacher.getId();
  }

  public byte[] getByteArray(InputStream is) throws Exception {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);

    while (true) {
      int i = is.read();
      if (i == -1) {
        break;
      }
      bufferedOutputStream.write(i);
    }
    bufferedOutputStream.flush();
    bufferedOutputStream.close();
    return byteArrayOutputStream.toByteArray();
  }

}
