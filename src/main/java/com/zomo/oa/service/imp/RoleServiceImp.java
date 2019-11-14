package com.zomo.oa.service.imp;

import com.zomo.oa.pojo.Role;
import com.zomo.oa.repository.RoleRepository;
import com.zomo.oa.service.RoleService;
import com.zomo.oa.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ServiceResponse<List<Role>> findAll() {

        return ServiceResponse.createBySuccess(roleRepository.findAll());
    }

    @Override
    public ServiceResponse<String> add(Role role) {
        role=roleRepository.save(role);
        if (role==null)
            return ServiceResponse.createByErrorMsg("添加角色失败");
        return ServiceResponse.createBySuccess("添加成功");
    }

    @Override
    public ServiceResponse<String> remove(Role role) {
        Role result=roleRepository.findOne(role.getId());
        if (result==null)
            return ServiceResponse.createByErrorMsg("角色信息错误，删除失败");
        roleRepository.delete(role);
        return ServiceResponse.createBySuccess("删除成功");
    }

    @Override
    public ServiceResponse<String> update(Role role) {
        Role result=roleRepository.findOne(role.getId());
        if (result==null)
            return ServiceResponse.createByErrorMsg("修改信息失败");
        role=roleRepository.save(role);
        if (role==null)
            return ServiceResponse.createByErrorMsg("修改信息失败");
        return ServiceResponse.createBySuccess("更新成功");
    }

    @Override
    public ServiceResponse<List<Role>> searchKeyWords(String str) {
        return ServiceResponse.createBySuccess(roleRepository.findByPositionLike("%"+str+"%"));
    }
}
