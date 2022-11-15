package com.example.mvcstudentapplicatn.db.repository;

import com.example.mvcstudentapplicatn.db.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentsRepository extends CrudRepository<Student, Integer> {
}
