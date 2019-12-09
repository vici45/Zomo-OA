package com.zomo.oa.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Personnel {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String phone;
    private String description;
    private Integer company;
    private String wechat;

    public Personnel(String name) {
    }

    public Personnel(String name, String phone, String description, Integer company, String wechat) {
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.company = company;
        this.wechat = wechat;
    }
}
