package com.zomo.oa.controller;

import com.zomo.oa.pojo.User;
import com.zomo.oa.service.UserService;
import com.zomo.oa.util.Const;
import com.zomo.oa.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ServiceResponse<User> register(User user) {


        return userService.register(user);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ServiceResponse<User> login(String username, String password, HttpSession session) {
        ServiceResponse<User> response = userService.login(username, password);
        if (response.isSuccess()) ;
        session.setAttribute(Const.CURRENT_USER, response.getData());
        return response;
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ServiceResponse<User> logout(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServiceResponse.createByErrorMsg("登出错误，用户并未登录");
        session.removeAttribute(Const.CURRENT_USER);
        return ServiceResponse.createBySuccessMsg("登出成功");
    }

    @RequestMapping(value = "getUserInfo", method = RequestMethod.GET)
    public ServiceResponse<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServiceResponse.createByErrorMsg("用户未登录无法获取信息");
        return ServiceResponse.createBySuccess(user);
    }

    @RequestMapping(value = "forgetGetQuestion", method = RequestMethod.POST)
    public ServiceResponse<String> forgetGetQuestion(String username, String email) {
        return userService.forget(username, email);
    }

    @RequestMapping(value = "forgetCheckAnswer", method = RequestMethod.POST)
    public ServiceResponse<String> forgetCheckAnswer(String username, String question, String answer) {
        return userService.checkAnswer(username, question, answer);
    }

    @RequestMapping(value = "valid", method = RequestMethod.POST)
    public ServiceResponse<String> valid(String str, String type) {
        if (type == null || type == "" || str == null || str == "")
            return ServiceResponse.createByErrorMsg("参数错误");
        return userService.checkValid(str, type);
    }

    @RequestMapping(value = "forgetResetPassword", method = RequestMethod.POST)
    public ServiceResponse<String> forgetResetPassword(String username, String newPassword, String token) {
        return userService.resetPasswordByToken(username, newPassword, token);
    }

    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    public ServiceResponse<String> resetPassword(HttpSession session, String oldPassword, String newPassword) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServiceResponse.createByErrorMsg("用户未登录");

        return userService.resetPassword(oldPassword, newPassword, user);
    }

    @RequestMapping(value = "updateInfo", method = RequestMethod.POST)
    public ServiceResponse<User> updateInfo(HttpSession session, User user) {
        User sessionUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (sessionUser == null) {
            return ServiceResponse.createByErrorMsg("用户未登录");
        }
        user.setId(sessionUser.getId());
        ServiceResponse response = userService.updateInfo(user);
        if (response.isSuccess())
            session.setAttribute(Const.CURRENT_USER, response.getData());
        return response;
    }

}
