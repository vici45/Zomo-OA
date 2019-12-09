package com.zomo.oa.repository;

import com.zomo.oa.pojo.ProjectPersonnel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectPersonnelRepository extends JpaRepository<ProjectPersonnel,Integer> {
    List<ProjectPersonnel> findByProjectSubDetailId(Integer projectSubDetailId);
    List<ProjectPersonnel> findByProjectSubDetailIdAndPay(Integer projectSubDetailId,Integer pay);
    List<ProjectPersonnel> findByPersonnelIdAndPay(Integer personnelId,Integer pay);
    List<ProjectPersonnel> findByPay(Integer pay);

}
