package com.zomo.oa.controller;

import com.zomo.oa.pojo.Cost;
import com.zomo.oa.pojo.User;
import com.zomo.oa.service.CostService;
import com.zomo.oa.util.Const;
import com.zomo.oa.util.ResponseCode;
import com.zomo.oa.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping(value = "cost/")
@RestController
public class CostController {
    @Autowired
    private CostService costService;

    @RequestMapping(value = "findOne",method = RequestMethod.POST)
    private ServiceResponse<Cost> findOne(HttpSession session,Integer id){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        if (id == null) {
            return ServiceResponse.createByErrorMsg("id不能为空");
        }
        return costService.findOne(id);
    }

    @RequestMapping(value = "findByProjectId",method = RequestMethod.POST)
    private ServiceResponse<List<Cost>> findByProjectId(HttpSession session, String projectId){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        if (projectId == null) {
            return ServiceResponse.createByErrorMsg("id不能为空");
        }
        return costService.findAllByProjectId(projectId);
    }

    @RequestMapping(value = "findByProjectIdAndPay",method = RequestMethod.POST)
    private ServiceResponse<List<Cost>> findByProjectIdAndPay(HttpSession session, String projectId,Integer pay){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        if (projectId == null||pay==null) {
            return ServiceResponse.createByErrorMsg("id、pay不能为空");
        }
        return costService.findAllByProjectIdAndPay(projectId,pay);
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    private ServiceResponse<String> add(HttpSession session, Cost cost){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }

        return costService.add(cost);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    private ServiceResponse<String> update(HttpSession session, Cost cost){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }

        return costService.update(cost);
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    private ServiceResponse<String> delete(HttpSession session, Integer id){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }

        return costService.delete(id);
    }

    @RequestMapping(value = "updatePay",method = RequestMethod.POST)
    private ServiceResponse<String> updatePay(HttpSession session, Integer id,Integer pay){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }

        return costService.updatePay(id,pay);
    }

}
