package com.zomo.oa.service.imp;

import com.zomo.oa.pojo.ProjectSubDetail;
import com.zomo.oa.repository.ProjectSubDetailRepository;
import com.zomo.oa.service.ProjectSubDetailService;
import com.zomo.oa.util.Const;
import com.zomo.oa.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectSubDetailServiceImp implements ProjectSubDetailService {
    @Autowired
    private ProjectSubDetailRepository projectSubDetailRepository;

    @Override
    public ServiceResponse<List<ProjectSubDetail>> findByProjectSubId(Integer projectSubId) {
        if (projectSubId == null) {
            return ServiceResponse.createByErrorMsg("id错误");
        }
        return ServiceResponse.createBySuccess(projectSubDetailRepository.findByProjectSubId(projectSubId));
    }

    @Override
    public ServiceResponse<ProjectSubDetail> findOne(Integer projectSubDetailId) {
        if (projectSubDetailId == null) {
            return ServiceResponse.createByErrorMsg("id错误");
        }
        ProjectSubDetail projectSubDetail=projectSubDetailRepository.findOne(projectSubDetailId);
        if (projectSubDetail == null) {
            return ServiceResponse.createByErrorMsg("id错误");
        }
        return ServiceResponse.createBySuccess(projectSubDetail);
    }

    @Override
    public ServiceResponse<List<ProjectSubDetail>> findByProjectSubIdAndProjectStatusId(Integer projectSubId, Integer projectStatusId) {
        if (projectStatusId==null||projectSubId==null)
            return ServiceResponse.createByErrorMsg("id或状态错误");
        List<ProjectSubDetail> list=projectSubDetailRepository.findByProjectSubIdAndpAndProjectStatusId(projectSubId,projectStatusId);
        return ServiceResponse.createBySuccess(list);
    }

    @Override
    public ServiceResponse<String> delete(Integer projectSubDetailId) {
        ProjectSubDetail projectSubDetail=projectSubDetailRepository.findOne(projectSubDetailId);
        if (projectSubDetail == null) {
            return ServiceResponse.createByErrorMsg("id错误");
        }
        projectSubDetailRepository.delete(projectSubDetailId);
        return ServiceResponse.createBySuccessMsg("删除成功");
    }

    @Override
    public ServiceResponse<String> add(ProjectSubDetail projectSubDetail) {
        projectSubDetail.setProjectStatusId(Const.PROJECT_STATUS_DEAFULT);
        projectSubDetail=projectSubDetailRepository.save(projectSubDetail);
        if (projectSubDetail == null) {
            return ServiceResponse.createByErrorMsg("添加失败");
        }
        return ServiceResponse.createBySuccessMsg("添加成功");
    }

    @Override
    public ServiceResponse<String> update(ProjectSubDetail projectSubDetail) {
        return null;
    }

    @Override
    public ServiceResponse<String> updateStatus(Integer projectSubDetailId, Integer statusId) {
        ProjectSubDetail projectSubDetail=projectSubDetailRepository.findOne(projectSubDetailId);
        if (projectSubDetail == null) {
            return ServiceResponse.createByErrorMsg("子项目id错误");
        }
        projectSubDetail.setProjectStatusId(statusId);
        projectSubDetail=projectSubDetailRepository.save(projectSubDetail);
        if (projectSubDetail == null) {
            return ServiceResponse.createByErrorMsg("修改状态失败");
        }
        return ServiceResponse.createBySuccessMsg("修改成功");
    }

    @Override
    public ServiceResponse<String> updateContent(Integer projectSubDetailId, Integer contentId) {
        ProjectSubDetail projectSubDetail=projectSubDetailRepository.findOne(projectSubDetailId);
        if (projectSubDetail == null) {
            return ServiceResponse.createByErrorMsg("子项目id错误");
        }
        projectSubDetail.setContent(contentId);

        projectSubDetail=projectSubDetailRepository.save(projectSubDetail);
        if (projectSubDetail == null) {
            return ServiceResponse.createByErrorMsg("修改内容e" +
                    "失败");
        }
        return ServiceResponse.createBySuccessMsg("修改成功");
    }
}
