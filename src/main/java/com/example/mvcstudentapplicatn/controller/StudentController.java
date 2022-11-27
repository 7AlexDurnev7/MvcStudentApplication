package com.example.mvcstudentapplicatn.controller;

import com.example.mvcstudentapplicatn.controller.filter.StudentNameFilter;
import com.example.mvcstudentapplicatn.db.entity.Group;
import com.example.mvcstudentapplicatn.db.entity.Student;
import com.example.mvcstudentapplicatn.service.GroupService;
import com.example.mvcstudentapplicatn.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService service; // сервис студентов

    @Autowired
    private StudentNameFilter containsFilter;   // объект фильтра

    @Autowired
    private GroupService groupService; // сервис групп

    // обработчик на получение страницы со списком всех студентов
    @GetMapping("/students")
    public String showAllStudents(Model model) {
        List<Student> listStudents = service.listAllStudents();
        model.addAttribute("listStudents", listStudents);
        return "student-list";
    }

    // обработчик на получение формы для добавления студента
    @GetMapping("/students/new")
    public String showNewStudentForm(Model model) {
        model.addAttribute("student", new Student());
        // добавим существующие группы в контекст
        List<Group> groups = groupService.listAllGroups(); // список всех групп
        model.addAttribute("groupsList", groups); // добавить в контекст представления
        return "student-form";
    }

    // обработчик для сохранения данных о пользователе
    @PostMapping("/students/new")
    public String saveNewStudent(Student student, RedirectAttributes ra) {
        // 1. сохраняем нового студента в БД
        Student saved = service.saveStudent(student);
        // 2. добавить сообщение о том, что студент добавлен
        ra.addFlashAttribute("message",
                "Student " + saved + " saved successfully");
        // 3. выполнить перенаправление
        return "redirect:/students";
    }

    // обработчик для удаления студент
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id, RedirectAttributes ra) {
        service.deleteStudentById(id);
        ra.addFlashAttribute("message", "Student deleted");
        return "redirect:/students";
    }

    // обработчик изменения данных студента
    @GetMapping("/students/update/{id}")
    public String showUpdateStudentForm(@PathVariable("id") Integer id, Model model) {
        Student student = service.getStudentById(id);
        model.addAttribute("student", student);
        // ВАЖНО: при возврате представления указывается имя представления
        return "student-update";
    }

    // обработчик для изменения данных о пользователе
    @PostMapping("/students/update")
    public String updateStudent(Student student, RedirectAttributes ra) {
        // изменяем поля
        Student updated = service.updateStudent(student);
        // сообщение о том, что данные изменены
        ra.addFlashAttribute("message",
                "Student " + updated + " update successfully");
        return "redirect:/students";
    }

    // обработчик для получения информации о пользователе по ID
    @GetMapping("/students/details/{id}")
    public String showDetails(@PathVariable("id") Integer id, Model model) {
        Student student = service.getStudentById(id);
        model.addAttribute("student", student);
        return "student-details";
    }

    @PostMapping("/students")
    public String showFilteredStudents(StudentNameFilter filter, Model model) {
        List<Student> studentList = filter.getFilteredStudents(service);
        model.addAttribute("studentsList", studentList);
        model.addAttribute("containsFilter", filter);
        return "student-list";
    }
}
