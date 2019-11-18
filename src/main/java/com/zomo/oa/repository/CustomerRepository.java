package com.zomo.oa.repository;

import com.zomo.oa.pojo.Customer;
import com.zomo.oa.util.ServiceResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    List<Customer> findByNameLike(String name);
    List<Customer> findByCompanyId(Integer companyId);
}
