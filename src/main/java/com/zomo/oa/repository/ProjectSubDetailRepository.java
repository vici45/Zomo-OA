package com.zomo.oa.repository;

import com.zomo.oa.pojo.ProjectSubDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectSubDetailRepository extends JpaRepository<ProjectSubDetail,Integer> {
    List<ProjectSubDetail> findByProjectSubId(Integer projectSubId);
    List<ProjectSubDetail> findByProjectSubIdAndpAndProjectStatusId(Integer projectSubId,Integer projectStatusId);
}
