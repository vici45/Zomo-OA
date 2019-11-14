package com.zomo.oa.repository;

import com.zomo.oa.pojo.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    List<Company> findByNameLike(String name);
}
