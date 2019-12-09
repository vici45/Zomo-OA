package com.zomo.oa.repository;

import com.zomo.oa.pojo.Personnel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonnelRepository extends JpaRepository<Personnel,Integer> {
    List<Personnel> findByCompany(Integer company);
}
