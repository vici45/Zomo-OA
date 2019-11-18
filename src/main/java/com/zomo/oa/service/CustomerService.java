package com.zomo.oa.service;

import com.zomo.oa.pojo.Customer;
import com.zomo.oa.util.ServiceResponse;

import java.util.List;

public interface CustomerService  {
    ServiceResponse<List<Customer>> findAll();
    ServiceResponse<List<Customer>> findByCompanyId(Integer companyId);
    ServiceResponse<String> update(Customer customer);
    ServiceResponse<String> add(Customer customer);
    ServiceResponse<String> delete(Integer id);
    ServiceResponse<List<Customer>> searchByKeyWord(String keyword);
    ServiceResponse<Customer> findOne(Integer id);
}
