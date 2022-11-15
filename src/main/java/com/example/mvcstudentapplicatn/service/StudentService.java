package com.example.mvcstudentapplicatn.service;

import com.example.mvcstudentapplicatn.db.entity.Student;
import com.example.mvcstudentapplicatn.db.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentsRepository repository;  // репозиторий
    // получить всех студентов через репозиторий
    public List<Student> listAllStudents() {
        return (List<Student>) repository.findAll();
    }

    // сохранить студента в БД
    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    // удаления студента по id
    public void deleteStudentById(Integer id) {
        // 1. найти студента для удаления
        Optional<Student> deleted = repository.findById(id);
        // 2. если такой студент есть, то удалить его
        deleted.ifPresent(student -> repository.delete(student));
    }

    public Student getStudentById(Integer id){
        Optional<Student> student = repository.findById(id);

        return student.orElse(null);
    }

    // обновить поля студента
    public Student updateStudent (Student student) {
        return repository.save(student);

    }
}
