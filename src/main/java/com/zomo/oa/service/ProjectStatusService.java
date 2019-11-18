package com.zomo.oa.service;

import com.zomo.oa.pojo.Company;
import com.zomo.oa.pojo.ProjectStatus;
import com.zomo.oa.util.ServiceResponse;

import java.util.List;

public interface ProjectStatusService {
    ServiceResponse<List<ProjectStatus>> findAll();
    ServiceResponse<String> add(ProjectStatus projectStatus);
    ServiceResponse<String> update(ProjectStatus projectStatus);
    ServiceResponse<String> delete(Integer id);
    ServiceResponse<ProjectStatus> findOne(Integer id);

}
