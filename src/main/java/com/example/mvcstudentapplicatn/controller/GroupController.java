package com.example.mvcstudentapplicatn.controller;

import com.example.mvcstudentapplicatn.db.entity.Group;
import com.example.mvcstudentapplicatn.db.repository.StudentsRepository;
import com.example.mvcstudentapplicatn.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/groups")
public class GroupController {
    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private final GroupService groupService; // сервис групп

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    // обработчик на получение страницы со списком всех групп
    @GetMapping
    public String showAllGroups(Model model) {
        List<Group> listGroups = groupService.listAllGroups();
        model.addAttribute("listGroups", listGroups);
        return "group-list";
    }

    // обработчик на получение формы для добавления студента
    @GetMapping("/new")
    public String showNewGroupForm(Model model) {
        model.addAttribute("group", new Group());
        // добавим существующие группы в контекст
//        List<Group> groups = groupService.listAllGroups(); // список всех групп
//        model.addAttribute("groupsList", groups); // добавить в контекст представления
        return "group-form";
    }

    // обработчик для сохранения данных о пользователе
    @PostMapping("/new")
    public String saveNewGroup(Group group, RedirectAttributes ra) {
        // 1. сохраняем нового студента в БД
        Group saved = groupService.saveGroup(group);
        // 2. добавить сообщение о том, что студент добавлен
        ra.addFlashAttribute("message",
                "Group " + saved + " saved successfully");
        // 3. выполнить перенаправление
        return "redirect:/groups";
    }

    // обработчик для удаления студент
    @GetMapping("/delete/{id}")
    public String deleteGroup(@PathVariable("id") Integer id, RedirectAttributes ra) {
        studentsRepository.clearGroupInStudentByGroup(id);
        groupService.deleteGroupById(id);
        ra.addFlashAttribute("message", "Group deleted");
        return "redirect:/groups";
    }

    // обработчик для получения информации о группе по ID
    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable("id") Integer id, Model model) {
        Group group = groupService.getGroupById(id);
        model.addAttribute("group", group);
        return "group-details";
    }

    // UPDATE
    // обработчик изменения данных студента
    @GetMapping("/update/{id}")
    public String showUpdateStudentForm(@PathVariable("id") Integer id, Model model) {
        Group group = groupService.getGroupById(id);
        model.addAttribute("group", group);
        // ВАЖНО: при возврате представления указывается имя представления
        return "group-update";
    }

    // обработчик для изменения данных о пользователе
    @PostMapping("/update")
    public String updateStudent(Group group, RedirectAttributes ra) {
        // изменяем поля
        Group updated = groupService.updateGroup(group);
        // сообщение о том, что данные изменены
        ra.addFlashAttribute("message",
                "Student " + updated + " update successfully");
        return "redirect:/groups";
    }
}
