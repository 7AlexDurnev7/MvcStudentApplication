package com.example.mvcstudentapplicatn.service;

import com.example.mvcstudentapplicatn.db.entity.Subject;
import com.example.mvcstudentapplicatn.db.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    // получить все предметы через репозиторий
    public List<Subject> listAllSubjects() {
        return (List<Subject>) subjectRepository.findAll();
    }

    // сохранить предмет в БД
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    // get subject by ID
    public Subject getSubjectById(Integer id){
        Optional<Subject> subject = subjectRepository.findById(id);
        return subject.orElse(null);
    }

    // удаления предмета по id
    public void deleteSubjectById(Integer id) {
        // 1. найти предмет для удаления
        Optional<Subject> deleted = subjectRepository.findById(id);
        // 2. если такой предмет есть, то удалить его
        deleted.ifPresent(subject -> subjectRepository.delete(subject));
    }

    // обновить поля предмета
    public Subject updateSubject (Subject subject) {
        return subjectRepository.save(subject);
    }
}
