package com.zomo.oa.service;

import com.zomo.oa.pojo.Content;
import com.zomo.oa.util.ServiceResponse;

import java.util.List;

public interface ContentService {
    ServiceResponse<List<Content>> findAll();
    ServiceResponse<List<Content>> findAllParent();
    ServiceResponse<List<Content>> findByParent(Integer parentId);
    ServiceResponse<Content> findOne(Integer id);
    ServiceResponse<String> add(Content content);
    ServiceResponse<String> update(Content content);
    ServiceResponse<String> delete(Integer id);
}
