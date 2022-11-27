package com.example.mvcstudentapplicatn.service;

import com.example.mvcstudentapplicatn.db.entity.Mark;
import com.example.mvcstudentapplicatn.db.entity.Student;
import com.example.mvcstudentapplicatn.db.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkService {

    @Autowired
    private MarkRepository markRepository;

    // вывод списка оценок
    public List<Mark> listAllMarks() {
        return (List<Mark>) markRepository.findAll();
    }

}
