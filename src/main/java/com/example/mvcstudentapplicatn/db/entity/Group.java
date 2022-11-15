package com.example.mvcstudentapplicatn.db.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="group_t")
public class Group {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 50)
    private String groupName;

    @OneToMany(mappedBy = "group")
    private Set<Student> students;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return id + " - " + groupName;
    }
}
