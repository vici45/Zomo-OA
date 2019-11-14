package com.zomo.oa.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Company {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String postAddress;

    private String tax;

    private String taxName;

    private String bankName;

    private String bankAccount;

    private String address;

    private String phone;

    public Company() {
    }


    public Company(String name, String postAddress, String tax, String taxName, String bankName, String bankAccount, String address, String phone) {
        this.name = name;
        this.postAddress = postAddress;
        this.tax = tax;
        this.taxName = taxName;
        this.bankName = bankName;
        this.bankAccount = bankAccount;
        this.address = address;
        this.phone = phone;
    }
}
