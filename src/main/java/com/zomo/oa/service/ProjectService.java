package com.zomo.oa.service;

import com.zomo.oa.pojo.Project;
import com.zomo.oa.util.ServiceResponse;

import java.util.List;

public interface ProjectService {
    ServiceResponse<List<Project>> findAll();
    ServiceResponse<List<Project>> findAllOnline();
    ServiceResponse<String> add(Project project);
    ServiceResponse<String> delete(String id);
    ServiceResponse<String> update(Project project);
    ServiceResponse<Project> findOne(String id);
    ServiceResponse<String> offline(String id);
}
