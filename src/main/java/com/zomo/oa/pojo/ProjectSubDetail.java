package com.zomo.oa.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class ProjectSubDetail {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer projectSubId;
    private Integer content;
    private String description;
    private Integer quantity;
    private Integer time;
    private BigDecimal cost;
    private BigDecimal total;
    private Integer projectStatusId;
    private Date beginTime;
    private Date endTime;

    public ProjectSubDetail() {
    }

    public ProjectSubDetail(Integer content, String description, Integer quantity, Integer time, BigDecimal cost, BigDecimal total, Integer projectStatusId, Date beginTime, Date endTime) {

        this.content = content;
        this.description = description;
        this.quantity = quantity;
        this.time = time;
        this.cost = cost;
        this.total = total;
        this.projectStatusId = projectStatusId;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }
}
