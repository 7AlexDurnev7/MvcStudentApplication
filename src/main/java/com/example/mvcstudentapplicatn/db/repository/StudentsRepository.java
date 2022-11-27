package com.example.mvcstudentapplicatn.db.repository;

import com.example.mvcstudentapplicatn.db.entity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface StudentsRepository extends CrudRepository<Student, Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE student_t SET group_id=NULL WHERE group_id=?1", nativeQuery = true)
    int clearGroupInStudentByGroup(int id);
}
