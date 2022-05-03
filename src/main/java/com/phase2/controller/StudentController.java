package com.phase2.controller;

import com.phase2.dao.GroupDao;
import com.phase2.dao.PhoneDao;
import com.phase2.dao.PhoneTypesDao;
import com.phase2.dao.StudentDao;
import com.phase2.model.Group;
import com.phase2.model.Phone;
import com.phase2.model.PhoneType;
import com.phase2.model.Student;
import com.phase2.model.StudentSearchForm;
import com.phase2.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Controller
public class StudentController {

  private StudentService studentService;
  @Autowired
  private StudentController studentController;

  @Resource
  private StudentDao studentDao;
  @Resource
  private GroupDao groupDao;
  @Resource
  private PhoneDao phoneDao;
  @Resource
  private PhoneTypesDao phoneTypesDao;

  public StudentController(StudentDao studentDao, StudentService studentService) {
    this.studentDao = studentDao;
    this.studentService = studentService;
  }

  @PostConstruct
  public void setUp() {
    studentDao.init();
  }

  @GetMapping("/students")
  public String listStudents(Model model) {

    StudentSearchForm studentSearchForm = new StudentSearchForm();
    studentSearchForm.setGender('A');
    List<Student> students = studentDao.findAll();
    model.addAttribute("students", students);
    List<Long> studentIds = new ArrayList<>();
    model.addAttribute("studentIds", studentIds);
    List<Group> groups = groupDao.getAllGroups();
    model.addAttribute("groups", groups);
    Map<Long, String> groupItems = new HashMap<>();
    for (Group group : groupDao.getAllGroups()) {
      groupItems.put(group.getId(), group.getName());
    }
    model.addAttribute("groups", groupItems);
    model.addAttribute("studentSearchForm", studentSearchForm);


    return "student-list";
  }

  @GetMapping("/editStudent/{id}")
  public String loadStudentById(@PathVariable long id, Model model) {
    model.addAttribute("student", studentDao.getStudentById(id));

    Map<Long, String> groupItems = new HashMap<>();
    for (Group group : groupDao.getAllGroups()) {
      groupItems.put(group.getId(), group.getName());
    }
    model.addAttribute("groupItems", groupItems);

    Map<String, String> selectedStatus = new HashMap<String, String>();
    selectedStatus.put(null, "NONE");
    selectedStatus.put("Active", "Active");
    selectedStatus.put("Suspended", "Suspended");
    model.addAttribute("selectedStatus", selectedStatus);

    List<Phone> phones = phoneDao.getAllPhones();
    model.addAttribute("phones", phones);

    List<PhoneType> phoneTypes = phoneTypesDao.getAllPhoneTypes();
    model.addAttribute("phoneTypes", phoneTypes);

    return "student-edit";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public ModelAndView submitStudent(@ModelAttribute("student") Student student) {

    return studentService.saveStudent(student);
  }

//  @RequestMapping(value = "/successSubmit", method = RequestMethod.GET)
//  public String successSubmit(Model model) {
//    model.addAttribute("student", studentDao.getStudentById(id));
//    return "student-edit";
//  }

  @RequestMapping(value = "/deleteStudent", method = RequestMethod.POST)
  public String deleteStudent(long[] ids) {
    for (long id : ids) {
//      Student student = studentDao.getStudentById(id);
//      student.setGroup(null);
////      studentDao.setGroupToNull(id);
      studentDao.deleteStudent(id);
    }
    return "redirect:/students";
  }

  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String createStudent(Model model) {
    Student student = new Student();
    model.addAttribute("student", student);

    Map<Long, String> groupItems = new HashMap<>();
    for (Group group : groupDao.getAllGroups()) {
      groupItems.put(group.getId(), group.getName());
    }
    model.addAttribute("groupItems", groupItems);

    Map<Integer, String> phoneTypeItems = new HashMap<>();
    for(PhoneType phoneType : phoneTypesDao.getAllPhoneTypes()) {
      phoneTypeItems.put(phoneType.getId(), phoneType.getName());
    }

    return "student-edit";
  }



  @RequestMapping(value = "/students/search", method = RequestMethod.POST)
  public String doSearchStudent(@ModelAttribute("studentSearchForm") StudentSearchForm studentSearchForm, Model model) {

    model.addAttribute("students", studentService.searchStudent(studentSearchForm));
    model.addAttribute("studentSearchForm", studentSearchForm);

    Map<Long, String> groupItems = new HashMap<>();
    for (Group group : groupDao.getAllGroups()) {
      groupItems.put(group.getId(), group.getName());
    }
    model.addAttribute("groups", groupItems);

    return "student-list";
  }

  @RequestMapping(value = "/save/image/{id}", method = RequestMethod.POST)
  public String saveImage(@RequestParam("picture") MultipartFile picture, @PathVariable long id) throws IOException {

    Student student = studentDao.getStudentById(id);
    String fileName = StringUtils.cleanPath(picture.getOriginalFilename());
    if (picture.getSize() <= 0) {
      student.setPicture(null);

    } else {
      try {
        byte[] bytes = picture.getBytes();
        String base64Picture = Base64.getEncoder().encodeToString(bytes);
        student.setPicture(base64Picture);
      } catch (Exception throwables) {
        throwables.printStackTrace();
      }
    }
    studentDao.save(student);
    return "redirect:/editStudent/" + student.getId();
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
