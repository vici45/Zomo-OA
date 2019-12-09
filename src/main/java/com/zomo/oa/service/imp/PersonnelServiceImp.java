package com.zomo.oa.service.imp;

import com.zomo.oa.pojo.Personnel;
import com.zomo.oa.repository.PersonnelRepository;
import com.zomo.oa.service.PersonnelService;
import com.zomo.oa.util.Const;
import com.zomo.oa.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class PersonnelServiceImp implements PersonnelService {
    @Autowired
    private PersonnelRepository personnelRepository;
    @Override
    public ServiceResponse<List<Personnel>> findAllByCompany(Integer company) {
        if (!(company==Const.PERSONNEL_IS_COMPANY||company==Const.PERSONNEL_NOT_COMPANY))
            return ServiceResponse.createByErrorMsg("参数错误");
        return ServiceResponse.createBySuccess(personnelRepository.findByCompany(company));
    }

    @Override
    public ServiceResponse<List<Personnel>> findAll() {
        return ServiceResponse.createBySuccess(personnelRepository.findAll());
    }

    @Override
    public ServiceResponse<String> add(Personnel personnel) {
        personnel.setCompany(Const.PERSONNEL_NOT_COMPANY);
        if (StringUtils.isEmpty(personnel.getName()))
            return ServiceResponse.createByErrorMsg("姓名不能为空");
        if (StringUtils.isEmpty(personnel.getPhone()))
            return ServiceResponse.createByErrorMsg("电话不能为空");
        if (StringUtils.isEmpty(personnel.getWechat()))
            return ServiceResponse.createByErrorMsg("微信不能为空");
        personnel=personnelRepository.save(personnel);
        if (personnel == null) {
            return ServiceResponse.createByErrorMsg("添加错误");
        }
        return ServiceResponse.createBySuccessMsg("添加人员成功");
    }

    @Override
    public ServiceResponse<String> update(Personnel personnel) {
        Integer id=personnel.getId();
        if (id==null)
            return ServiceResponse.createByErrorMsg("参数错误");
        Personnel result=personnelRepository.findOne(id);
        if (result == null) {
            return ServiceResponse.createByErrorMsg("id错误");
        }
        if (!StringUtils.isEmpty(personnel.getName()))
            result.setName(personnel.getName());
        if (!StringUtils.isEmpty(personnel.getPhone()))
            result.setPhone(personnel.getPhone());
        if (!StringUtils.isEmpty(personnel.getWechat()))
            result.setWechat(personnel.getWechat());
        result=personnelRepository.save(result);
        if (result == null) {
            return ServiceResponse.createByErrorMsg("更新失败");
        }
        return ServiceResponse.createBySuccessMsg("更新成功");
    }

    @Override
    public ServiceResponse<String> delete(Integer id) {
        if (id == null) {
            return ServiceResponse.createByErrorMsg("id不能为空");
        }
        Personnel personnel=personnelRepository.findOne(id);
        if (personnel == null) {
            return ServiceResponse.createByErrorMsg("人员id错误");
        }
        if (personnel.getCompany()==Const.PERSONNEL_IS_COMPANY)
            return ServiceResponse.createByErrorMsg("公司员工不能删除");
        personnelRepository.delete(personnel);
        return ServiceResponse.createBySuccessMsg("删除人员成功");
    }
}
