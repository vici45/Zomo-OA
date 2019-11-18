package com.zomo.oa.repository;

import com.zomo.oa.pojo.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,String> {
    List<Project> findByProjectStatusIdNot(Integer id);
    Project findByProjectStatusIdNotAndId(Integer projectStatusId,String id);
    Project findByProjectStatusIdAndId(Integer projectStatusId, String id);
}
