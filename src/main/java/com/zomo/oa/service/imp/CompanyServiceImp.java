package com.zomo.oa.service.imp;

import com.zomo.oa.pojo.Company;
import com.zomo.oa.repository.CompanyRepository;
import com.zomo.oa.service.CompanyService;
import com.zomo.oa.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompanyServiceImp implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public ServiceResponse<List<Company>> findAll() {

        return ServiceResponse.createBySuccess(companyRepository.findAll());
    }

    @Override
    public ServiceResponse<String> addOne(Company company) {
        company = companyRepository.save(company);
        if (company == null)
            return ServiceResponse.createByErrorMsg("添加公司信息失败");
        return ServiceResponse.createBySuccessMsg("添加公司信息成功");
    }

    @Override
    public ServiceResponse<String> update(Company company) {
        Integer id = company.getId();
        if (id == null)
            return ServiceResponse.createByErrorMsg("公司信息错误");
        Company result = companyRepository.findOne(id);
        if (result == null)
            return ServiceResponse.createByErrorMsg("公司信息错误");
        result=companyRepository.save(company);
        if (result == null) {
            return ServiceResponse.createByErrorMsg("更新公司信息失败");
        }
        return ServiceResponse.createBySuccessMsg("更新公司信息成功");
    }

    @Override
    public ServiceResponse<String> delete(Integer id) {
        Company company=companyRepository.findOne(id);
        if (company == null) {
            return ServiceResponse.createByErrorMsg("公司id错误");
        }
        companyRepository.delete(id);
        //todo 是否要查询已被删除？
        return ServiceResponse.createBySuccessMsg("公司信息删除成功");
    }

    @Override
    public ServiceResponse<List<Company>> searchKeyword(String keyWords) {

        return ServiceResponse.createBySuccess(companyRepository.findByNameLike("%"+keyWords+"%"));
    }

    @Override
    public ServiceResponse<Company> findOne(Integer id) {
        Company company=companyRepository.findOne(id);
        if (company == null) {
            return ServiceResponse.createByErrorMsg("公司id错误");
        }
        return ServiceResponse.createBySuccess(company);
    }


}
