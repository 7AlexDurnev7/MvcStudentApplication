package com.example.mvcstudentapplicatn.service;

import com.example.mvcstudentapplicatn.db.entity.Group;
import com.example.mvcstudentapplicatn.db.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    // репозиторий групп
    @Autowired
    private GroupRepository repository;

    // получение списка всех групп
    public List<Group> listAllGroups() {
        return (List<Group>)repository.findAll();
    }

    // сохранить группу в БД
    public Group saveGroup(Group group) {
        return repository.save(group);
    }

    // удаления группу по id
    public void deleteGroupById(Integer id) {
        // 1. найти группу для удаления
        Optional<Group> deleted = repository.findById(id);
        // 2. если такая группа есть, то удалить ее
        deleted.ifPresent(group -> repository.delete(group));
    }

    // получаем группу по ID
    public Group getGroupById(Integer id){
        Optional<Group> group = repository.findById(id);
        return group.orElse(null);
    }

    // обновить поля группы
    public Group updateGroup (Group group) {
        return repository.save(group);
    }
}
