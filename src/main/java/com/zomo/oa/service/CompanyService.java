package com.zomo.oa.service;

import com.zomo.oa.pojo.Company;
import com.zomo.oa.util.ServiceResponse;

import java.util.List;

public interface CompanyService {
    ServiceResponse<List<Company>> findAll();
    ServiceResponse<String> addOne(Company company);
    ServiceResponse<String> update(Company company);
    ServiceResponse<String> delete(Integer id);
    ServiceResponse<List<Company>> searchKeyword(String keyWords);
}
