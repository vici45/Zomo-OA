package com.zomo.oa.service;

import com.zomo.oa.pojo.ProjectSub;
import com.zomo.oa.util.ServiceResponse;

import java.util.List;

public interface ProjectSubService {
    ServiceResponse<List<ProjectSub>> findByProjectId(String projectId);
    ServiceResponse<ProjectSub> findOne(Integer id);
    ServiceResponse<String> add(ProjectSub projectSub);
    ServiceResponse<String> delete(Integer id);
    ServiceResponse<String> update(ProjectSub projectSub);
    ServiceResponse<String> updateStatus(Integer id,Integer status);
}
