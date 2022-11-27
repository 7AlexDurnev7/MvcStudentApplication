package com.example.mvcstudentapplicatn.controller.filter;

import com.example.mvcstudentapplicatn.db.entity.Student;
import com.example.mvcstudentapplicatn.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentNameFilter {

    private String match = "";   // строка фильтра
    public String getMatch() {
        return match;
    }
    public void setMatch(String match) {
        this.match = match;
    }

    public List<Student> getFilteredStudents(StudentService service) {
        // фильтрация студентов на основе включения match в имя или фамилию
        return service.findByContains(match);
    }
}
