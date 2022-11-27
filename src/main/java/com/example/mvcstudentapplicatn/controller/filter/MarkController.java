package com.example.mvcstudentapplicatn.controller.filter;

import com.example.mvcstudentapplicatn.db.entity.Mark;
import com.example.mvcstudentapplicatn.db.entity.Student;
import com.example.mvcstudentapplicatn.service.GroupService;
import com.example.mvcstudentapplicatn.service.MarkService;
import com.example.mvcstudentapplicatn.service.StudentService;
import com.example.mvcstudentapplicatn.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/marks")
public class MarkController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private MarkService markService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public String showAllMarks(Model model) {
        List<Mark> listMark = markService.listAllMarks();
        model.addAttribute("listMark", listMark);
        return "marks-list";
    }
}
