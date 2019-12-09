package com.zomo.oa.service;

import com.zomo.oa.pojo.Personnel;
import com.zomo.oa.util.ServiceResponse;

import java.util.List;

public interface PersonnelService {
    ServiceResponse<List<Personnel>> findAllByCompany(Integer company);
    ServiceResponse<List<Personnel>> findAll();
    ServiceResponse<String> add(Personnel personnel);
    ServiceResponse<String> update(Personnel personnel);
    ServiceResponse<String> delete(Integer id);


}
