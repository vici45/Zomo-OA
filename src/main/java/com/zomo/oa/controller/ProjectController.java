package com.zomo.oa.controller;

import com.zomo.oa.pojo.Project;
import com.zomo.oa.pojo.User;
import com.zomo.oa.service.ProjectService;
import com.zomo.oa.util.Const;
import com.zomo.oa.util.ResponseCode;
import com.zomo.oa.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public ServiceResponse<List<Project>> findAll(HttpSession session){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectService.findAllOnline();

    }

    @RequestMapping(value = "findOne",method = RequestMethod.POST)
    public ServiceResponse<Project> findOne(HttpSession session,String id){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectService.findOne(id);

    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public ServiceResponse<String> add(HttpSession session,Project project){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        project.setCreateUserId(user.getId());
        project.setProjectStatusId(Const.INIT_PROJECT_STATUS_ID);
        return projectService.add(project);

    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public ServiceResponse<String> update(HttpSession session,Project project){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectService.update(project);

    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public ServiceResponse<String> delete(HttpSession session,String id){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectService.delete(id);

    }

    @RequestMapping(value = "offLine",method = RequestMethod.POST)
    public ServiceResponse<String> offLine(HttpSession session,String id){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        return projectService.offline(id);

    }


}
