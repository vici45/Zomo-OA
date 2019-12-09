package com.zomo.oa.repository;

import com.zomo.oa.pojo.ProjectSub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectSubRepository extends JpaRepository<ProjectSub,Integer> {
    List<ProjectSub> findByProjectId(String projectId);
}
