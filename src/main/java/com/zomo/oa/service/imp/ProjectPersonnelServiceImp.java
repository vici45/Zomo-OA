package com.zomo.oa.service.imp;

import com.zomo.oa.pojo.Personnel;
import com.zomo.oa.pojo.ProjectPersonnel;
import com.zomo.oa.pojo.ProjectSubDetail;
import com.zomo.oa.repository.PersonnelRepository;
import com.zomo.oa.repository.ProjectPersonnelRepository;
import com.zomo.oa.repository.ProjectSubDetailRepository;
import com.zomo.oa.service.ProjectPersonnelService;
import com.zomo.oa.util.Const;
import com.zomo.oa.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectPersonnelServiceImp implements ProjectPersonnelService {
    @Autowired
    private ProjectPersonnelRepository projectPersonnelRepository;
    @Autowired
    private ProjectSubDetailRepository projectSubDetailRepository;
    @Autowired
    private PersonnelRepository personnelRepository;

    @Override
    public ServiceResponse<List<ProjectPersonnel>> findByProjectSubDetailId(Integer projectSubDetailId) {
        if (projectSubDetailId == null) {
            return ServiceResponse.createByErrorMsg("参数错误");
        }
        ProjectSubDetail projectSubDetail = projectSubDetailRepository.findOne(projectSubDetailId);
        if (projectSubDetail == null) {
            return ServiceResponse.createByErrorMsg("id错误");
        }
        return ServiceResponse.createBySuccess(projectPersonnelRepository.findByProjectSubDetailId(projectSubDetailId));
    }

    @Override
    public ServiceResponse<List<ProjectPersonnel>> findByProjectSubDetailIdAndPay(Integer projectSubDetailId, Integer pay) {
        if (projectSubDetailId == null || pay != Const.COST_NOT_PAY || pay != Const.COST_PAY) {
            return ServiceResponse.createByErrorMsg("参数错误");
        }
        ProjectSubDetail projectSubDetail = projectSubDetailRepository.findOne(projectSubDetailId);
        if (projectSubDetail == null) {
            return ServiceResponse.createByErrorMsg("id错误");
        }
        return ServiceResponse.createBySuccess(projectPersonnelRepository.findByProjectSubDetailIdAndPay(projectSubDetailId, pay));
    }

    @Override
    public ServiceResponse<List<ProjectPersonnel>> findByPersonnelIdAndPay(Integer personnelId, Integer pay) {
        if (personnelId == null || pay != Const.COST_NOT_PAY || pay != Const.COST_PAY) {
            return ServiceResponse.createByErrorMsg("参数错误");
        }

        Personnel personnel = personnelRepository.findOne(personnelId);
        if (personnel == null) {
            return ServiceResponse.createByErrorMsg("参数错误");
        }
        return ServiceResponse.createBySuccess(projectPersonnelRepository.findByPersonnelIdAndPay(personnelId, pay));
    }

    @Override
    public ServiceResponse<List<ProjectPersonnel>> findByPay(Integer pay) {
        if (pay != Const.COST_NOT_PAY || pay != Const.COST_PAY) {
            return ServiceResponse.createByErrorMsg("参数错误");
        }
        return ServiceResponse.createBySuccess(projectPersonnelRepository.findByPay(pay));
    }

    @Override
    public ServiceResponse<String> add(ProjectPersonnel projectPersonnel) {
        Integer id = projectPersonnel.getId();
        Personnel personnel = personnelRepository.getOne(id);
        if (personnel == null) {
            return ServiceResponse.createByErrorMsg("参数错误");
        }
        Integer projectSubDetailId = projectPersonnel.getProjectSubDetailId();
        ProjectSubDetail projectSubDetail = projectSubDetailRepository.getOne(projectSubDetailId);
        if (projectSubDetail == null) {
            return ServiceResponse.createByErrorMsg("参数错误");
        }
        Integer pay=projectPersonnel.getPay();
        if (pay == null) {
            pay=Const.COST_NOT_PAY;
            projectPersonnel.setPay(pay);
        }
        projectPersonnel=projectPersonnelRepository.save(projectPersonnel);
        if (projectPersonnel == null) {
            return ServiceResponse.createByErrorMsg("添加失败");
        }
        return ServiceResponse.createBySuccessMsg("添加成功");
    }

    @Override
    public ServiceResponse<String> delete(Integer id) {
        ProjectPersonnel projectPersonnel=projectPersonnelRepository.getOne(id);
        if (projectPersonnel == null) {
            return ServiceResponse.createByErrorMsg("参数错误");
        }
        projectPersonnelRepository.delete(id);
        return ServiceResponse.createBySuccessMsg("删除成功");
    }

    @Override
    public ServiceResponse<String> update(ProjectPersonnel projectPersonnel) {
        return null;
    }

    @Override
    public ServiceResponse<ProjectPersonnel> findOne(Integer id) {
        ProjectPersonnel projectPersonnel=projectPersonnelRepository.getOne(id);
        if (projectPersonnel == null) {
            return ServiceResponse.createByErrorMsg("id错误");
        }
        return ServiceResponse.createBySuccess(projectPersonnel);
    }

    @Override
    public ServiceResponse<String> updatePay(Integer id, Integer pay) {
        if (pay!=Const.COST_PAY&&pay!=Const.COST_NOT_PAY)
            return ServiceResponse.createByErrorMsg("参数错误");
        ProjectPersonnel projectPersonnel=projectPersonnelRepository.getOne(id);
        if (projectPersonnel == null) {
            return ServiceResponse.createByErrorMsg("id错误");
        }
        projectPersonnel.setPay(pay);
        projectPersonnel=projectPersonnelRepository.save(projectPersonnel);
        if (projectPersonnel == null) {
            return ServiceResponse.createByErrorMsg("更新错误");
        }
        return ServiceResponse.createBySuccessMsg("更新成功");
    }
}
