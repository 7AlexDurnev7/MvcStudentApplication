package com.example.mvcstudentapplicatn.db.repository;

import com.example.mvcstudentapplicatn.db.entity.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<Subject, Integer> {
}
