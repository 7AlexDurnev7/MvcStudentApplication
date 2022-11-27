package com.example.mvcstudentapplicatn.db.repository;

import com.example.mvcstudentapplicatn.db.entity.Group;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface GroupRepository extends CrudRepository<Group, Integer> {

}
