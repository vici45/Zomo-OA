package com.zomo.oa.controller;


import com.zomo.oa.pojo.Company;
import com.zomo.oa.pojo.User;
import com.zomo.oa.service.CompanyService;
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
@RequestMapping(value = "company/")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public ServiceResponse<List<Company>> findAll(HttpSession session){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null)
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        return companyService.findAll();
    }


    @RequestMapping(value = "add",method = RequestMethod.POST)
    public ServiceResponse<String> add(HttpSession session,Company company){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null)
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        return companyService.addOne(company);
    }

    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public ServiceResponse<String> delete(HttpSession session,Integer id){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null)
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        return companyService.delete(id);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public ServiceResponse<String> update(HttpSession session,Company company){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null)
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        return companyService.update(company);
    }

    @RequestMapping(value = "search",method = RequestMethod.GET)
    public ServiceResponse<List<Company>> search(HttpSession session,String keyWord){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null)
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        return companyService.searchKeyword(keyWord);
    }
}
