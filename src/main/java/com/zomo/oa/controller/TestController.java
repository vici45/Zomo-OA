package com.zomo.oa.controller;

import com.zomo.oa.pojo.Role;
import com.zomo.oa.pojo.User;
import com.zomo.oa.util.ServiceResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @RequestMapping(value = "test",method = RequestMethod.POST)
    public ServiceResponse<Role> test(Role[] role
    ){
        return ServiceResponse.createBySuccess(role[1]);
    }
}
