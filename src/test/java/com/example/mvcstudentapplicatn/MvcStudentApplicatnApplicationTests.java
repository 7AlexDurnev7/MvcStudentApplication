package com.example.mvcstudentapplicatn;

import com.example.mvcstudentapplicatn.db.entity.Student;
import com.example.mvcstudentapplicatn.db.repository.StudentsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MvcStudentApplicatnApplicationTests {

    private StudentsRepository repository;


    @Test
    public void testCreateStudent() {
        Student newStudent = new Student();
        newStudent.setFirstName("Igor");
        newStudent.setLastName("Igorev");
        Student saved = repository.save(newStudent);
        System.out.println(saved);
    }
}
