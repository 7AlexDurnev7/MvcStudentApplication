package com.example.mvcstudentapplicatn.controller;

import com.example.mvcstudentapplicatn.db.entity.Group;
import com.example.mvcstudentapplicatn.db.entity.Student;
import com.example.mvcstudentapplicatn.db.entity.Subject;
import com.example.mvcstudentapplicatn.service.SubjectService;
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
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    // READ (получить все предметы)
    @GetMapping
    public String showAllSubjects(Model model) {
        List<Subject> listSubjects = subjectService.listAllSubjects();
        model.addAttribute("listSubjects", listSubjects);
        return "subjects-list";
    }

    // CREATE (добавить предмет)
    @GetMapping("/newSubject")
    public String showNewSubjectForm(Model model) {
        model.addAttribute("subject", new Subject());
        return "subjects-form";
    }

    // Обработчик для сохранения предмета
    @PostMapping("/newSubject")
    public String saveNewSubject(Subject subject, RedirectAttributes attrs) {
        Subject saved = subjectService.saveSubject(subject);
        attrs.addFlashAttribute("message",
                "Subject " + saved + " saved successfully");
        return "redirect:/subjects";
    }

    // DELETE (обработчик для удаления предмета)
    @GetMapping("/delete/{id}")
    public String deleteSubject(@PathVariable("id") Integer id, RedirectAttributes attrs) {
        subjectService.deleteSubjectById(id);
        attrs.addFlashAttribute("message", "Subject deleted");
        return "redirect:/subjects";
    }

    // обработчик изменения данных предмета
    @GetMapping("/update/{id}")
    public String showUpdateSubjectForm(@PathVariable("id") Integer id, Model model) {
        Subject subject = subjectService.getSubjectById(id);
        model.addAttribute("subject", subject);
        // ВАЖНО: при возврате представления указывается имя представления
        return "subjects-update";
    }

    // обработчик для изменения данных о пользователе
    @PostMapping("/update")
    public String updateSubject(Subject subject, RedirectAttributes ra) {
        // изменяем поля
        Subject updated = subjectService.updateSubject(subject);
        // сообщение о том, что данные изменены
        ra.addFlashAttribute("message",
                "Subject " + updated + " update successfully");
        return "redirect:/subjects";
    }
}
