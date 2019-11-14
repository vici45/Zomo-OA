package com.zomo.oa.service;

import com.zomo.oa.pojo.Role;
import com.zomo.oa.util.ServiceResponse;

import java.util.List;

public interface RoleService {
    ServiceResponse<List<Role>> findAll();

    ServiceResponse<String> add(Role role);

    ServiceResponse<String> remove(Role role);

    ServiceResponse<String> update(Role role);

    ServiceResponse<List<Role>> searchKeyWords(String str);
}
