package com.zomo.oa.service.imp;

import com.zomo.oa.pojo.ProjectStatus;
import com.zomo.oa.repository.ProjectStatusRepository;
import com.zomo.oa.service.ProjectStatusService;
import com.zomo.oa.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectStatusServiceImp implements ProjectStatusService {
    @Autowired
    private ProjectStatusRepository projectStatusRepository;
    @Override
    public ServiceResponse<List<ProjectStatus>> findAll() {
        return ServiceResponse.createBySuccess(projectStatusRepository.findAll());
    }

    @Override
    public ServiceResponse<String> add(ProjectStatus projectStatus) {
        String name=projectStatus.getName();
        if (name==null||name=="")
            return ServiceResponse.createByErrorMsg("名称不能为空");
        projectStatus=projectStatusRepository.save(projectStatus);
        if (projectStatus == null) {
            return ServiceResponse.createByErrorMsg("添加错误");
        }
        return ServiceResponse.createBySuccessMsg("添加成功");
    }

    @Override
    public ServiceResponse<String> update(ProjectStatus projectStatus) {
        Integer id=projectStatus.getId();
        if (id==null)
            return ServiceResponse.createByErrorMsg("id为空，无法更新");
        projectStatus=projectStatusRepository.findOne(id);
        if (projectStatus==null)
            return ServiceResponse.createByErrorMsg("用户id错误");
        String name=projectStatus.getName();
        if (name==null||null=="")
            return ServiceResponse.createByErrorMsg("名称不能为空");
        projectStatus=projectStatusRepository.save(projectStatus);
        if (projectStatus == null) {
            return ServiceResponse.createByErrorMsg("用户更新错误");
        }
        return ServiceResponse.createBySuccess("更新成功");
    }

    @Override
    public ServiceResponse<String> delete(Integer id) {
        ProjectStatus projectStatus=projectStatusRepository.findOne(id);
        if (projectStatus == null) {
            return ServiceResponse.createByErrorMsg("id错误，无法删除");
        }
        projectStatusRepository.delete(id);
        return ServiceResponse.createBySuccessMsg("删除成功");
    }

    @Override
    public ServiceResponse<ProjectStatus> findOne(Integer id) {
        ProjectStatus projectStatus=projectStatusRepository.findOne(id);
        if (projectStatus == null) {
            return ServiceResponse.createByErrorMsg("id错误无法获取");
        }
        return ServiceResponse.createBySuccess(projectStatus);
    }
}
