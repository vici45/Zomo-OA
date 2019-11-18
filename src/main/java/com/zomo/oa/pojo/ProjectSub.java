package com.zomo.oa.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class ProjectSub {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String projectId;
    private BigDecimal account;
    private Integer projectStatusId;

    public ProjectSub() {
    }

    public ProjectSub(String name, String projectId, BigDecimal account, Integer projectStatusId) {
        this.name = name;
        this.projectId = projectId;
        this.account = account;
        this.projectStatusId = projectStatusId;
    }
}
