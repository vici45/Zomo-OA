package com.zomo.oa.service.imp;

import com.zomo.oa.pojo.Project;
import com.zomo.oa.pojo.ProjectStatus;
import com.zomo.oa.pojo.ProjectSub;
import com.zomo.oa.repository.ProjectRepository;
import com.zomo.oa.repository.ProjectStatusRepository;
import com.zomo.oa.repository.ProjectSubRepository;
import com.zomo.oa.service.ProjectSubService;
import com.zomo.oa.util.Const;
import com.zomo.oa.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class ProjectSubServiceImp implements ProjectSubService {
    @Autowired
    private ProjectSubRepository projectSubRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectStatusRepository projectStatusRepository;
    @Override
    public ServiceResponse<List<ProjectSub>> findByProjectId(String projectId) {
        Project project=projectRepository.findOne(projectId);
        if (project == null) {
            return ServiceResponse.createByErrorMsg("项目id错误");
        }

        return ServiceResponse.createBySuccess(projectSubRepository.findByProjectId(projectId));
    }

    @Override
    public ServiceResponse<ProjectSub> findOne(Integer id) {
        if (id == null) {
            return ServiceResponse.createByErrorMsg("项目id不能为空");
        }
        ProjectSub projectSub=projectSubRepository.findOne(id);
        if (projectSub == null) {
            return ServiceResponse.createByErrorMsg("子项目id错误");
        }
        return ServiceResponse.createBySuccess(projectSub);
    }

    @Override
    public ServiceResponse<String> add(ProjectSub projectSub) {
        String projectId=projectSub.getProjectId();
        if (projectId == null) {
            return ServiceResponse.createByErrorMsg("项目id不能为空");
        }
        Project project=projectRepository.findOne(projectId);
        if (project == null) {
            return ServiceResponse.createByErrorMsg("项目id错误");
        }
        projectSub.setProjectStatusId(Const.PROJECT_STATUS_DEAFULT);
        projectSub=projectSubRepository.save(projectSub);
        if (projectSub == null) {
            return ServiceResponse.createByErrorMsg("添加项目失败");
        }
        return ServiceResponse.createBySuccessMsg("子项目添加成功");
    }

    @Override
    public ServiceResponse<String> delete(Integer id) {
        if (id == null) {
            return ServiceResponse.createBySuccessMsg("id不能为空");
        }
        ProjectSub projectSub=projectSubRepository.findOne(id);
        if (projectSub == null) {
            return ServiceResponse.createByErrorMsg("子项目id错误");
        }
        projectSubRepository.delete(projectSub);
        return ServiceResponse.createBySuccessMsg("子项目删除成功");
    }

    @Override
    public ServiceResponse<String> update(ProjectSub projectSub) {
        Integer id=projectSub.getId();
        if (id == null) {
            return ServiceResponse.createBySuccessMsg("id不能为空");
        }
        ProjectSub result=projectSubRepository.findOne(id);
        if (result == null) {
            return ServiceResponse.createBySuccessMsg("参数错误");
        }
        if (!StringUtils.isEmpty(projectSub.getName()))
            result.setName(projectSub.getName());
        if (projectSub.getProjectId()!=null&&projectRepository.findOne(projectSub.getProjectId())!=null)
            result.setProjectId(projectSub.getProjectId());
        if (projectSub.getProjectStatusId()!=null)
            result.setProjectStatusId(projectSub.getProjectStatusId());
        projectSub=projectSubRepository.save(result);
        if (projectSub == null) {
            return ServiceResponse.createByErrorMsg("更新错误");
        }
        return ServiceResponse.createBySuccessMsg("更新成功");
    }

    @Override
    public ServiceResponse<String> updateStatus(Integer id, Integer status) {
        if (id==null||status==null)
            return ServiceResponse.createByErrorMsg("参数不能为空");
        ProjectSub projectSub=projectSubRepository.findOne(id);
        if (projectSub == null) {
            return ServiceResponse.createByErrorMsg("id错误");
        }
        ProjectStatus projectStatus=projectStatusRepository.getOne(status);
        if (projectStatus == null) {
            return ServiceResponse.createByErrorMsg("status错误");
        }
        projectSub.setProjectStatusId(status);
        projectSub=projectSubRepository.save(projectSub);
        if (projectSub == null) {
            return ServiceResponse.createByErrorMsg("更新失败");
        }
        return ServiceResponse.createBySuccessMsg("更新成功");
    }
}
