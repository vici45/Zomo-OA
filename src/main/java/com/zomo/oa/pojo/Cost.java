package com.zomo.oa.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class Cost {

    @Id
    @GeneratedValue
    private Integer id;
    private String projectId;
    private BigDecimal cost;
    private String description;
    private Integer pay;
    private Integer userId;
    private Date createTime;

    public Cost() {
    }

    public Cost(String projectId, BigDecimal cost, String description, Integer pay, Integer userId, Date createTime) {
        this.projectId = projectId;
        this.cost = cost;
        this.description = description;
        this.pay = pay;
        this.userId = userId;
        this.createTime = createTime;
    }
}
