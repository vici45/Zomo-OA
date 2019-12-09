package com.zomo.oa.repository;

import com.zomo.oa.pojo.Cost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CostRepository extends JpaRepository<Cost,Integer> {
    List<Cost> findByProjectId(String projectId);
    List<Cost> findByProjectIdAndPay(String projectId,Integer pay);
}
