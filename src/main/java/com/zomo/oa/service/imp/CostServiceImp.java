package com.zomo.oa.service.imp;

import com.zomo.oa.pojo.Cost;
import com.zomo.oa.pojo.Project;
import com.zomo.oa.repository.CostRepository;
import com.zomo.oa.repository.ProjectRepository;
import com.zomo.oa.service.CostService;
import com.zomo.oa.util.Const;
import com.zomo.oa.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class CostServiceImp implements CostService {
    @Autowired
    private CostRepository costRepository;
    @Autowired
    private ProjectRepository projectRepository;
//todo user_id 0为公司支付 其他则为对应人员
    @Override
    public ServiceResponse<List<Cost>> findAllByProjectId(String projectId) {
        if (projectId == Const.COST_PROJECTID_COMPANY)
            return ServiceResponse.createBySuccess(costRepository.findByProjectId(projectId));
        Project project = projectRepository.findOne(projectId);
        if (project == null) {
            return ServiceResponse.createByErrorMsg("项目id错误");
        }
        return ServiceResponse.createBySuccess(costRepository.findByProjectId(projectId));
    }

    @Override
    public ServiceResponse<List<Cost>> findAllByProjectIdAndPay(String projectId, Integer pay) {
        if (StringUtils.isEmpty(projectId))
            return ServiceResponse.createByErrorMsg("项目id不能为空");
        Project project = projectRepository.findOne(projectId);
        if (project == null) {
            return ServiceResponse.createByErrorMsg("项目id错误");
        }
        if (!(pay == Const.COST_PAY || pay == Const.COST_NOT_PAY))
            return ServiceResponse.createByErrorMsg("参数错误");

        return ServiceResponse.createBySuccess(costRepository.findByProjectIdAndPay(projectId, pay));
    }

    @Override
    public ServiceResponse<Cost> findOne(Integer id) {
        if (id == null) {
            return ServiceResponse.createByErrorMsg("参数错误");
        }
        Cost cost=costRepository.findOne(id);
        if (cost == null) {
            return ServiceResponse.createByErrorMsg("id错误");
        }
        return ServiceResponse.createBySuccess(cost);
    }

    @Override
    public ServiceResponse<String> add(Cost cost) {
        String projectId = cost.getProjectId();
        if (StringUtils.isEmpty(projectId))
            return ServiceResponse.createByErrorMsg("项目id错误");
        if (cost.getPay()==null)
            cost.setPay(Const.COST_NOT_PAY);
        if (projectId == Const.COST_PROJECTID_COMPANY)
            cost = costRepository.save(cost);
        else {
            Project project = projectRepository.findOne(projectId);
            if (project == null) {
                return ServiceResponse.createByErrorMsg("项目id错误");
            }
            cost = costRepository.save(cost);
        }
        if (cost == null) {
            return ServiceResponse.createByErrorMsg("成本添加错误");
        }
        return ServiceResponse.createBySuccessMsg("添加成功");
    }

    @Override
    public ServiceResponse<String> delete(Integer id) {
        if (id == null)
            return ServiceResponse.createByErrorMsg("id不能为空");
        Cost cost = costRepository.findOne(id);
        if (cost == null) {
            return ServiceResponse.createByErrorMsg("id错误删除失败");
        }
        costRepository.delete(cost);
        return ServiceResponse.createByErrorMsg("成本删除成功");
    }

    @Override
    public ServiceResponse<String> update(Cost cost) {
        Integer id = cost.getId();
        if (id == null)
            return ServiceResponse.createByErrorMsg("参数错误");
        Cost result = costRepository.findOne(id);
        if (result == null) {
            return ServiceResponse.createByErrorMsg("id错误");
        }
        if (cost.getCost() != null)
            result.setCost(cost.getCost());
        if (cost.getDescription() != null)
            result.setDescription(cost.getDescription());
        if (cost.getPay() != null)
            result.setPay(cost.getPay());
        if (cost.getUserId() != null)
            result.setUserId(cost.getUserId());
        if (result.getPay() != Const.COST_NOT_PAY && result.getPay() != Const.COST_PAY)
            return ServiceResponse.createByErrorMsg("参数错误");
        result = costRepository.save(result);
        if (result == null) {
            return ServiceResponse.createByErrorMsg("修改失败");
        }
        return ServiceResponse.createBySuccessMsg("修改成功");
    }

    @Override
    public ServiceResponse<String> updatePay(Integer id, Integer pay) {
        Cost cost = costRepository.getOne(id);
        if (cost == null) {
            return ServiceResponse.createBySuccessMsg("参数错误");
        }
        if (pay != Const.COST_NOT_PAY && pay != Const.COST_PAY)
            return ServiceResponse.createBySuccessMsg("参数错误");
        cost.setPay(pay);
        cost = costRepository.save(cost);
        if (cost == null) {
            return ServiceResponse.createBySuccessMsg("修改失败");
        }
        return ServiceResponse.createBySuccessMsg("修改成功");
    }
}
