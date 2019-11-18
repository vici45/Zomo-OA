package com.zomo.oa.controller;

import com.zomo.oa.VO.CompanyToCustomerVo;
import com.zomo.oa.pojo.Company;
import com.zomo.oa.pojo.Customer;
import com.zomo.oa.pojo.User;
import com.zomo.oa.service.CompanyService;
import com.zomo.oa.service.CustomerService;
import com.zomo.oa.util.Const;
import com.zomo.oa.util.ResponseCode;
import com.zomo.oa.util.ServiceResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "customer/")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "findAllCompany", method = RequestMethod.GET)
    public ServiceResponse<List<CompanyToCustomerVo>> findAllCompany(HttpSession session) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());

        List<CompanyToCustomerVo> list = new ArrayList<>();
        ServiceResponse response = companyService.findAll();
        if (!response.isSuccess() || response.getData() == null)
            return ServiceResponse.createByErrorMsg("公司列表错误或为空");

        List<Company> companyList = (List<Company>) response.getData();
        for (Company company : companyList) {
            CompanyToCustomerVo c = new CompanyToCustomerVo();
            BeanUtils.copyProperties(company, c);
            list.add(c);
        }

        return ServiceResponse.createBySuccess(list);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ServiceResponse<String> add(HttpSession session, Customer customer) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        return customerService.add(customer);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ServiceResponse<String> update(HttpSession session, Customer customer) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        return customerService.update(customer);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ServiceResponse<String> delete(HttpSession session, Integer id) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        return customerService.delete(id);
    }

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public ServiceResponse<List<Customer>> findAll(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        return customerService.findAll();
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public ServiceResponse<List<Customer>> search(HttpSession session, String keyWord) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        return customerService.searchByKeyWord(keyWord);
    }

    @RequestMapping(value = "findByCompanyId", method = RequestMethod.POST)
    public ServiceResponse<List<Customer>> findByCompanyId(HttpSession session, Integer companyId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        return customerService.findByCompanyId(companyId);
    }

    @RequestMapping(value = "findOne", method = RequestMethod.POST)
    public ServiceResponse<Customer> findOne(HttpSession session, Integer id) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServiceResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        return customerService.findOne(id);
    }


}
