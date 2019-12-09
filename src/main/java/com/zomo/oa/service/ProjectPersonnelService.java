package com.zomo.oa.service;

import com.zomo.oa.pojo.ProjectPersonnel;
import com.zomo.oa.util.ServiceResponse;

import java.util.List;

public interface ProjectPersonnelService {
    ServiceResponse<List<ProjectPersonnel>> findByProjectSubDetailId(Integer projectSubDetailId);
    ServiceResponse<List<ProjectPersonnel>> findByProjectSubDetailIdAndPay(Integer projectSubDetailId,Integer pay);
    ServiceResponse<List<ProjectPersonnel>> findByPersonnelIdAndPay(Integer personnelId,Integer pay);
    ServiceResponse<List<ProjectPersonnel>> findByPay(Integer pay);
    ServiceResponse<String> add(ProjectPersonnel projectPersonnel);
    ServiceResponse<String> delete(Integer id);
    ServiceResponse<String> update(ProjectPersonnel projectPersonnel);
    ServiceResponse<ProjectPersonnel> findOne(Integer id);
    ServiceResponse<String> updatePay(Integer id ,Integer pay);
}
