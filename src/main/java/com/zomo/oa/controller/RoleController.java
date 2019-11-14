package com.zomo.oa.controller;

import com.zomo.oa.pojo.Role;
import com.zomo.oa.pojo.User;
import com.zomo.oa.service.RoleService;
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
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "addRole", method = RequestMethod.POST)
    public ServiceResponse<String> addRole(Role role, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());

        return roleService.add(role);
    }

    @RequestMapping(value = "removeRole", method = RequestMethod.POST)
    public ServiceResponse<String> removeRole(Role role, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());

        return roleService.remove(role);
    }

    @RequestMapping(value = "updateRole", method = RequestMethod.POST)
    public ServiceResponse<String> updateRole(Role role, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());

        return roleService.update(role);
    }

    @RequestMapping(value = "searchRole", method = RequestMethod.POST)
    public ServiceResponse<List<Role>> searchRole(String keyWord, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());

        return roleService.searchKeyWords(keyWord);
    }

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public ServiceResponse<List<Role>> findAll(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());

        return roleService.findAll();
    }
}
