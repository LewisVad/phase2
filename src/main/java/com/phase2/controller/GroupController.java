package com.phase2.controller;

import com.phase2.dao.GroupDao;
import com.phase2.dao.impl.DefaultGroupDao;
import com.phase2.model.Group;

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
public class GroupController {

  @Autowired
  private GroupDao groupDao;

  public GroupController(GroupDao groupDao) {
    this.groupDao = groupDao;
  }

//  public GroupController(GroupController groupController) {
//  }

  @GetMapping("/groups")
  public String listGroups(Model model) {
    List<Group> groups = groupDao.getAllGroups();
    List<Long> contain = groupDao.groupsWithStudents();
    model.addAttribute("groups", groups);

    return "group-list";
  }

  @GetMapping("/group/{id}")
  public String editGroup(@PathVariable long id, Model model) {
    if (id != 0) {
      Group group = groupDao.findById(id);
      model.addAttribute("group", group);
    } else {
      Group group = new Group();
      model.addAttribute("group", group);
    }
    return "group-edit";
  }

  @RequestMapping(value = "/newGroup", method = RequestMethod.GET)
  public String createGroup(Model model) {
    Group group = new Group();
    model.addAttribute("group", group);
    return "group-edit";
  }

  @Valid
  @RequestMapping(value = "/saveGroup", method = RequestMethod.POST)
  public ModelAndView submitGroup(@ModelAttribute Group groupForm) throws IOException {
    Group group = groupForm.getId() == 0 ? new Group() : groupDao.findById(groupForm.getId());
    String groupName = groupForm.getName();
    if (!("".equals(groupName))) {
      group.setName(groupName);
      groupDao.saveGroup(group);
    }

    return new ModelAndView("redirect:/group/" + group.getId());
  }

  @PostMapping("/deleteGroup/{id}")
  public String deleteGroup(@PathVariable long id) {
    List<Long> contain = groupDao.groupsWithStudents();

    if (contain.contains(id)) {
      groupDao.deleteGroup(id);
      return "redirect:/groups";
    } else {
      return "delete-group";
    }
  }

  public void setGroupDao(DefaultGroupDao groupDao) {
  }
}
