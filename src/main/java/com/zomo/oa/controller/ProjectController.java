package com.zomo.oa.controller;

import com.zomo.oa.pojo.*;
import com.zomo.oa.service.ProjectPersonnelService;
import com.zomo.oa.service.ProjectService;
import com.zomo.oa.service.ProjectSubDetailService;
import com.zomo.oa.service.ProjectSubService;
import com.zomo.oa.util.Const;
import com.zomo.oa.util.ResponseCode;
import com.zomo.oa.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "project/")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectSubService projectSubService;
    @Autowired
    private ProjectSubDetailService projectSubDetailService;
    @Autowired
    private ProjectPersonnelService projectPersonnelService;

    @RequestMapping(value = "findAllProject",method = RequestMethod.GET)
    public ServiceResponse<List<Project>> findAllProject(HttpSession session){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectService.findAllOnline();

    }

    @RequestMapping(value = "findOneProject",method = RequestMethod.POST)
    public ServiceResponse<Project> findOneProject(HttpSession session,String id){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectService.findOne(id);

    }

    @RequestMapping(value = "addProject",method = RequestMethod.POST)
    public ServiceResponse<String> addProject(HttpSession session,Project project){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        project.setCreateUserId(user.getId());
        project.setProjectStatusId(Const.INIT_PROJECT_STATUS_ID);
        return projectService.add(project);

    }

    @RequestMapping(value = "updateProject",method = RequestMethod.POST)
    public ServiceResponse<String> updateProject(HttpSession session,Project project){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectService.update(project);

    }

    @RequestMapping(value = "deleteProject",method = RequestMethod.POST)
    public ServiceResponse<String> deleteProject(HttpSession session,String id){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectService.delete(id);

    }

    @RequestMapping(value = "offLineProject",method = RequestMethod.POST)
    public ServiceResponse<String> offLineProject(HttpSession session,String id){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectService.offline(id);
    }

    @RequestMapping(value = "findSub",method = RequestMethod.POST)
    public ServiceResponse<List<ProjectSub>> findSub(String projectId,HttpSession session){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        ServiceResponse response=findOneProject(session,projectId);
        if (!response.isSuccess())
            return response;
        return projectSubService.findByProjectId(projectId);
    }

    @RequestMapping(value = "findOneSub",method = RequestMethod.POST)
    public ServiceResponse<ProjectSub> findOneSub(HttpSession session,Integer subId){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectSubService.findOne(subId);
    }

    @RequestMapping(value = "addSub",method = RequestMethod.POST)
    public ServiceResponse<String> addSub(HttpSession session,ProjectSub projectSub){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectSubService.add(projectSub);
    }

    @RequestMapping(value = "updateSub",method = RequestMethod.POST)
    public ServiceResponse<String> updateSub(HttpSession session,ProjectSub projectSub){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectSubService.update(projectSub);
    }

    @RequestMapping(value = "updateSubStatus",method = RequestMethod.POST)
    public ServiceResponse<String> updateSubStatus(HttpSession session,Integer projectSubId,Integer statusId){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectSubService.updateStatus(projectSubId,statusId);
    }

    @RequestMapping(value = "findDetailBySubId",method = RequestMethod.POST)
    public ServiceResponse<List<ProjectSubDetail>> findDetailBySubId(HttpSession session,Integer projectSubId){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectSubDetailService.findByProjectSubId(projectSubId);
    }

    @RequestMapping(value = "findDetailBySubIdAndStatus",method = RequestMethod.POST)
    public ServiceResponse<List<ProjectSubDetail>> findDetailBySubId(HttpSession session,Integer projectSubId,Integer statusId){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectSubDetailService.findByProjectSubIdAndProjectStatusId(projectSubId,statusId);
    }

    @RequestMapping(value = "findDetailId",method = RequestMethod.POST)
    public ServiceResponse<ProjectSubDetail> findDetailId(HttpSession session,Integer projectSubDetailId){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectSubDetailService.findOne(projectSubDetailId);
    }

    @RequestMapping(value = "addProjectDetail",method = RequestMethod.POST)
    public ServiceResponse<String> addProjectDetail(HttpSession session,ProjectSubDetail projectSubDetail){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectSubDetailService.add(projectSubDetail);
    }

    @RequestMapping(value = "deleteProjectDetail",method = RequestMethod.POST)
    public ServiceResponse<String> deleteProjectDetail(HttpSession session,Integer projectSubDetailId){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectSubDetailService.delete(projectSubDetailId);
    }

    @RequestMapping(value = "updateProjectDetailStatus",method = RequestMethod.POST)
    public ServiceResponse<String> updateProjectDetailStatus(HttpSession session,Integer projectSubDetailId,Integer statusId){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectSubDetailService.updateStatus(projectSubDetailId,statusId);
    }

    @RequestMapping(value = "updateProjectDetailContent",method = RequestMethod.POST)
    public ServiceResponse<String> updateProjectDetailContent(HttpSession session,Integer projectSubDetailId,Integer contentID){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectSubDetailService.updateContent(projectSubDetailId,contentID);
    }

    @RequestMapping(value = "findProjectPersonnelByDetailId",method = RequestMethod.POST)
    public ServiceResponse<List<ProjectPersonnel>> findProjectPersonnelByDetailId(HttpSession session, Integer projectSubDetailId){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectPersonnelService.findByProjectSubDetailId(projectSubDetailId);
    }

    @RequestMapping(value = "findProjectPersonnelByDetailIdAndPay",method = RequestMethod.POST)
    public ServiceResponse<List<ProjectPersonnel>> findProjectPersonnelByDetailIdAndPay(HttpSession session, Integer projectSubDetailId,Integer pay){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectPersonnelService.findByProjectSubDetailIdAndPay(projectSubDetailId,pay);
    }

    @RequestMapping(value = "findProjectPersonnelByIdAndPay",method = RequestMethod.POST)
    public ServiceResponse<List<ProjectPersonnel>> findProjectPersonnelByIdAndPay(HttpSession session, Integer projectPersonnelId,Integer pay){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectPersonnelService.findByPersonnelIdAndPay(projectPersonnelId,pay);
    }

    @RequestMapping(value = "findProjectPersonnelById",method = RequestMethod.POST)
    public ServiceResponse<ProjectPersonnel> findProjectPersonnelById(HttpSession session, Integer projectPersonnelId){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectPersonnelService.findOne(projectPersonnelId);
    }

    @RequestMapping(value = "addProjectPersonnel",method = RequestMethod.POST)
    public ServiceResponse<String> addProjectPersonnel(HttpSession session, ProjectPersonnel ProjectPersonnel){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectPersonnelService.add(ProjectPersonnel);
    }

    @RequestMapping(value = "updateProjectPersonnel",method = RequestMethod.POST)
    public ServiceResponse<String> updateProjectPersonnel(HttpSession session, ProjectPersonnel ProjectPersonnel){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectPersonnelService.update(ProjectPersonnel);
    }

    @RequestMapping(value = "updateProjectPersonnelPay",method = RequestMethod.POST)
    public ServiceResponse<String> updateProjectPersonnelPay(HttpSession session, Integer ProjectPersonnelId,Integer pay){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectPersonnelService.updatePay(ProjectPersonnelId,pay);
    }

    @RequestMapping(value = "deleteProjectPersonnel",method = RequestMethod.POST)
    public ServiceResponse<String> deleteProjectPersonnel(HttpSession session, Integer ProjectPersonnelId){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectPersonnelService.delete(ProjectPersonnelId);
    }



}
