package com.zomo.oa.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Content {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer parent;

    public Content(String name, Integer parent) {
        this.name = name;
        this.parent = parent;
    }

    public Content() {
    }
}
