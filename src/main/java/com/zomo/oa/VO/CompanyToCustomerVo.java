package com.zomo.oa.VO;

import lombok.Data;

@Data
public class CompanyToCustomerVo {
    private Integer id;
    private String name;

    public CompanyToCustomerVo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public CompanyToCustomerVo() {
    }
}
