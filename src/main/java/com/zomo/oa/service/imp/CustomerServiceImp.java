package com.zomo.oa.service.imp;

import com.zomo.oa.pojo.Company;
import com.zomo.oa.pojo.Customer;
import com.zomo.oa.repository.CompanyRepository;
import com.zomo.oa.repository.CustomerRepository;
import com.zomo.oa.service.CustomerService;
import com.zomo.oa.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImp implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public ServiceResponse<List<Customer>> findAll() {
        return ServiceResponse.createBySuccess(customerRepository.findAll());
    }

    @Override
    public ServiceResponse<List<Customer>> findByCompanyId(Integer companyId) {
        return ServiceResponse.createBySuccess(customerRepository.findByCompanyId(companyId));
    }

    @Override
    public ServiceResponse<String> update(Customer customer) {
        Integer id = customer.getId();
        if (id == null) {
            return ServiceResponse.createByErrorMsg("客户id为空，更新失败");
        }
        Integer companyId = customer.getCompanyId();
        if (companyId == null) {
            return ServiceResponse.createByErrorMsg("公司id为为空，更新失败");
        }
        Customer resultCustomer = customerRepository.findOne(id);
        if (resultCustomer == null) {
            return ServiceResponse.createByErrorMsg("客户id错误,更新失败");
        }
        Company companyResult=companyRepository.findOne(companyId);
        if (companyResult == null) {
            return ServiceResponse.createByErrorMsg("公司id错误，更新失败");
        }
        String name=customer.getName();
        if (name == null||name.equals("")) {
            return ServiceResponse.createByErrorMsg("用户名不能为空");
        }
        customer=customerRepository.save(customer);
        if (customer == null) {
            return ServiceResponse.createByErrorMsg("更新失败");
        }
        return ServiceResponse.createBySuccessMsg("更新成功");
    }

    @Override
    public ServiceResponse<String> add(Customer customer) {
        Integer companyId = customer.getCompanyId();
        if (companyId == null) {
            return ServiceResponse.createByErrorMsg("公司id为空");
        }
        Company company = companyRepository.findOne(companyId);
        if (company == null) {
            return ServiceResponse.createByErrorMsg("公司id错误");
        }
        String name=customer.getName();
        if (name == null||name.equals("")) {
            return ServiceResponse.createByErrorMsg("用户名不能为空");
        }
        customer = customerRepository.save(customer);
        if (customer == null) {
            return ServiceResponse.createByErrorMsg("新增客户错误");
        }
        return ServiceResponse.createBySuccessMsg("新增用户成功");
    }

    @Override
    public ServiceResponse<String> delete(Integer id) {
        Customer customer = customerRepository.findOne(id);
        if (customer == null) {
            return ServiceResponse.createByErrorMsg("用户id错误，删除失败");
        }
        customerRepository.delete(id);
        customer = customerRepository.findOne(id);
        if (customer != null) {
            return ServiceResponse.createByErrorMsg("删除用户失败");
        }
        return ServiceResponse.createBySuccessMsg("删除用户成功");
    }

    @Override
    public ServiceResponse<List<Customer>> searchByKeyWord(String keyword) {
        return ServiceResponse.createBySuccess(customerRepository.findByNameLike("%"+keyword+"%"));
    }

    @Override
    public ServiceResponse<Customer> findOne(Integer id) {
        Customer customer=customerRepository.findOne(id);
        if (customer == null) {
            return ServiceResponse.createByErrorMsg("客户id错误");
        }
        return ServiceResponse.createBySuccess(customer);
    }
}
