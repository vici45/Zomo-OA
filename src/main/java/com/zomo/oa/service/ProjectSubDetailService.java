package com.zomo.oa.service;

import com.zomo.oa.pojo.ProjectSubDetail;
import com.zomo.oa.util.ServiceResponse;

import java.util.List;

public interface ProjectSubDetailService {
    ServiceResponse<List<ProjectSubDetail>> findByProjectSubId(Integer projectSubId);
    ServiceResponse<ProjectSubDetail> findOne(Integer projectSubDetailId);
    ServiceResponse<List<ProjectSubDetail>> findByProjectSubIdAndProjectStatusId(Integer projectSubId,Integer projectStatusId);
    ServiceResponse<String> delete(Integer projectSubDetailId);
    ServiceResponse<String> add(ProjectSubDetail projectSubDetail);
    ServiceResponse<String> update(ProjectSubDetail projectSubDetail);
    ServiceResponse<String> updateStatus(Integer projectSubDetailId,Integer statusId);
    ServiceResponse<String> updateContent(Integer projectSubDetailId,Integer contentId);



}
