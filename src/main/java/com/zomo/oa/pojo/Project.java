package com.zomo.oa.pojo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class Project {

    @Id
    private String id;
    private String name;
    private String address;
    private Date beginTime;
    private Date endTime;
    private Integer customerId;
    private Integer leaderId;
    private BigDecimal costs;
    private Integer createUserId;
    private Integer projectStatusId;
    private BigDecimal account;
    private BigDecimal tax;
    private BigDecimal total;
    //todo 开票状态

    public Project(String id, String name, String address, Date beginTime, Date endTime, Integer customerId, Integer leaderId, BigDecimal costs, Integer createUserId, Integer projectStatusId, BigDecimal account, BigDecimal tax, BigDecimal total, Date createTime) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.customerId = customerId;
        this.leaderId = leaderId;
        this.costs = costs;
        this.createUserId = createUserId;
        this.projectStatusId = projectStatusId;
        this.account = account;
        this.tax = tax;
        this.total = total;
        this.createTime = createTime;
    }

    private Date createTime;

    public Project() {
    }
}
