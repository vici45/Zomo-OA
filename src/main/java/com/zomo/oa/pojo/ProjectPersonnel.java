package com.zomo.oa.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class ProjectPersonnel {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer personnelId;
    private Integer projectSubDetailId;
    private BigDecimal cost;
    private BigDecimal total;
    private BigDecimal days;
    private BigDecimal numbers;
    private Integer pay;
    private String comment;

    public ProjectPersonnel() {
    }

    public ProjectPersonnel(Integer personnelId, Integer projectSubDetailId, BigDecimal cost, BigDecimal total, BigDecimal days, BigDecimal numbers, Integer pay, String comment) {
        this.personnelId = personnelId;
        this.projectSubDetailId = projectSubDetailId;
        this.cost = cost;
        this.total = total;
        this.days = days;
        this.numbers = numbers;
        this.pay = pay;
        this.comment = comment;
    }
}
