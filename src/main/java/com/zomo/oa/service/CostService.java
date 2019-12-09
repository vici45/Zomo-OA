package com.zomo.oa.service;

import com.zomo.oa.pojo.Cost;
import com.zomo.oa.util.ServiceResponse;

import java.util.List;

public interface CostService {
    ServiceResponse<List<Cost>> findAllByProjectId(String projectId);
    ServiceResponse<List<Cost>> findAllByProjectIdAndPay(String projectId,Integer pay);
    ServiceResponse<Cost> findOne(Integer id);
    ServiceResponse<String> add(Cost cost);
    ServiceResponse<String> delete(Integer id);
    ServiceResponse<String> update(Cost cost);
    ServiceResponse<String> updatePay(Integer id,Integer pay);

}
