package com.zomo.oa.controller;

import com.zomo.oa.pojo.Content;
import com.zomo.oa.pojo.User;
import com.zomo.oa.service.ContentService;
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
@RequestMapping(value = "content/")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public ServiceResponse<List<Content>> findAll(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return contentService.findAll();
    }

    @RequestMapping(value = "findAllParent", method = RequestMethod.GET)
    public ServiceResponse<List<Content>> findAllParent(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return contentService.findAllParent();
    }

    @RequestMapping(value = "findOne", method = RequestMethod.POST)
    public ServiceResponse<Content> findOne(HttpSession session,Integer id) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return contentService.findOne(id);
    }

    @RequestMapping(value = "findByParent", method = RequestMethod.POST)
    public ServiceResponse<List<Content>> findByParent(HttpSession session,Integer parent) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return contentService.findByParent(parent);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ServiceResponse<String> add(HttpSession session,Content content) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return contentService.add(content);
    }
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ServiceResponse<String> delete(HttpSession session,Integer id) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return contentService.delete(id);
    }
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ServiceResponse<String> update(HttpSession session,Content content) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return contentService.update(content);
    }
}
