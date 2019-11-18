package com.zomo.oa.service.imp;

import com.zomo.oa.pojo.Project;
import com.zomo.oa.repository.ProjectRepository;
import com.zomo.oa.service.ProjectService;
import com.zomo.oa.util.Const;
import com.zomo.oa.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProjectServiceImp implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Override
    public ServiceResponse<List<Project>> findAll() {
        return ServiceResponse.createBySuccess(projectRepository.findAll());
    }

    @Override
    public ServiceResponse<List<Project>> findAllOnline() {
        return ServiceResponse.createBySuccess(projectRepository.findByProjectStatusIdNot(Const.PROJECT_STATUS_OFFLINE));
    }

    @Override
    public ServiceResponse<String> add(Project project) {
        String id=UUID.randomUUID().toString();
        project.setId(id);
        project=projectRepository.save(project);
        if (project == null) {
            return ServiceResponse.createByErrorMsg("添加项目失败");
        }
        return ServiceResponse.createBySuccess("添加项目成功");
    }

    @Override
    public ServiceResponse<String> delete(String id) {
        Project project=projectRepository.findByProjectStatusIdAndId(Const.PROJECT_STATUS_OFFLINE,id);
        if (project == null) {
            return ServiceResponse.createByErrorMsg("删除失败");
        }
        projectRepository.delete(project);
        return ServiceResponse.createBySuccessMsg("删除成功");
    }

    @Override
    public ServiceResponse<String> update(Project project) {
        String id=project.getId();
        if (StringUtils.isEmpty(id)) {
            return ServiceResponse.createByErrorMsg("项目id错误更新失败");
        }
        Project result=projectRepository.findByProjectStatusIdNotAndId(Const.PROJECT_STATUS_OFFLINE,id);
        if (result == null) {
            return ServiceResponse.createByErrorMsg("项目id错误，或项目状态错误,更新项目失败");
        }
        if (!StringUtils.isEmpty(project.getName()))
            result.setName(project.getName());
        if (!StringUtils.isEmpty(project.getAddress()))
            result.setAddress(project.getAddress());
        if (project.getAccount()!=null)
            result.setAccount(project.getAccount());
        if (project.getBeginTime()!=null)
            result.setBeginTime(project.getBeginTime());
        if (project.getEndTime()!=null)
            result.setEndTime(project.getEndTime());
        if (project.getCustomerId()!=null)
            result.setCustomerId(project.getCustomerId());
        if (project.getLeaderId()!=null)
            result.setLeaderId(project.getLeaderId());
        result=projectRepository.save(result);
        if (result == null) {
            return ServiceResponse.createByErrorMsg("更新失败");
        }
        return ServiceResponse.createBySuccessMsg("更新成功");
    }

    @Override
    public ServiceResponse<Project> findOne(String id) {
        Project project=projectRepository.findByProjectStatusIdNotAndId(Const.PROJECT_STATUS_OFFLINE,id);
        if (project == null) {
            return ServiceResponse.createByErrorMsg("id错误");
        }
        return ServiceResponse.createBySuccess(project);
    }

    @Override
    public ServiceResponse<String> offline(String id) {
        Project project=projectRepository.findByProjectStatusIdNotAndId(Const.PROJECT_STATUS_OFFLINE,id);
        if (project == null) {
            return ServiceResponse.createByErrorMsg("id错误或已下架");
        }
        project.setProjectStatusId(Const.PROJECT_STATUS_OFFLINE);
        project=projectRepository.save(project);
        if (project == null) {
            return ServiceResponse.createByErrorMsg("下架错误");
        }
        return ServiceResponse.createBySuccessMsg("下架成功");
    }
}
