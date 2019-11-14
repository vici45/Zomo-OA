package com.zomo.oa.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String phone;

    private String email;

    private String wechat;

    private Integer companyId;

    public Customer() {

    }

    public Customer(String name, String phone, String email, String wechat, Integer companyId) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.wechat = wechat;
        this.companyId = companyId;
    }
}
