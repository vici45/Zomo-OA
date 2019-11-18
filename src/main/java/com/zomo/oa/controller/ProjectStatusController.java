package com.zomo.oa.controller;

import com.zomo.oa.pojo.ProjectStatus;
import com.zomo.oa.pojo.User;
import com.zomo.oa.service.ProjectStatusService;
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
@RequestMapping(value = "projectStatus/")
public class ProjectStatusController {
    @Autowired
    private ProjectStatusService projectStatusService;

    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public ServiceResponse<List<ProjectStatus>> findAll(HttpSession session){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null)
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());

        return projectStatusService.findAll();
    }
    @RequestMapping(value = "findOne",method = RequestMethod.POST)
    public ServiceResponse<ProjectStatus> findOne(HttpSession session,Integer id){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null)
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());

        return projectStatusService.findOne(id);
    }
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public ServiceResponse<String> add(HttpSession session,ProjectStatus projectStatus){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null)
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());

        return projectStatusService.add(projectStatus);
    }
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public ServiceResponse<String> delete(HttpSession session,Integer id){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null)
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());

        return projectStatusService.delete(id);
    }
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public ServiceResponse<String> update(HttpSession session,ProjectStatus projectStatus){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null)
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());

        return projectStatusService.update(projectStatus);
    }
}
