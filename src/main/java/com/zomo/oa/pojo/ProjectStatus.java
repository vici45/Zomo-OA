package com.zomo.oa.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class ProjectStatus {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    public ProjectStatus(String name) {
        this.name = name;
    }

    public ProjectStatus() {
    }
}
