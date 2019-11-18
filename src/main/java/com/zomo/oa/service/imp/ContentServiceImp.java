package com.zomo.oa.service.imp;

import com.zomo.oa.pojo.Content;
import com.zomo.oa.repository.ContentRepository;
import com.zomo.oa.service.ContentService;
import com.zomo.oa.util.Const;
import com.zomo.oa.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContentServiceImp implements ContentService {
    @Autowired
    private ContentRepository contentRepository;

    @Override
    public ServiceResponse<List<Content>> findAll() {
        return ServiceResponse.createBySuccess(contentRepository.findAll());
    }

    @Override
    public ServiceResponse<List<Content>> findAllParent() {
        return ServiceResponse.createBySuccess(contentRepository.findByParent(Const.CONTENT_PARENT_ID));
    }

    @Override
    public ServiceResponse<List<Content>> findByParent(Integer parentId) {
        if (parentId == null)
            return ServiceResponse.createByErrorMsg("parent不能为空");
        return ServiceResponse.createBySuccess(contentRepository.findByParent(parentId));
    }

    @Override
    public ServiceResponse<Content> findOne(Integer id) {
        if (id == null) {
            return ServiceResponse.createByErrorMsg("id不能为空");
        }
        Content content = contentRepository.findOne(id);
        if (content == null) {
            return ServiceResponse.createByErrorMsg("id错误，请重试");
        }
        return null;
    }

    @Override
    public ServiceResponse<String> add(Content content) {
        Integer parent = content.getParent();
        if (parent == null) {
            content.setParent(0);
        }
        content = contentRepository.save(content);
        if (content == null) {
            return ServiceResponse.createByErrorMsg("添加错误");
        }
        return ServiceResponse.createBySuccessMsg("添加成功");
    }

    @Override
    public ServiceResponse<String> update(Content content) {
        Integer id = content.getId();
        if (id == null) {
            return ServiceResponse.createBySuccessMsg("id不能为空");
        }
        Content result = contentRepository.findOne(id);
        if (result == null) {
            return ServiceResponse.createByErrorMsg("id错误，无法修改");
        }
        if (content.getParent() != null)
            result.setParent(content.getParent());
        if (content.getName() != null)
            result.setName(content.getName());
        result = contentRepository.save(result);
        if (result == null) {
            return ServiceResponse.createByErrorMsg("更新错误");
        }
        return ServiceResponse.createBySuccessMsg("更新成功");
    }

    @Override
    public ServiceResponse<String> delete(Integer id) {
        Content result = contentRepository.findOne(id);
        if (result == null)
            return ServiceResponse.createByErrorMsg("id错误");
        List<Content> list = contentRepository.findByParent(result.getId());
        if (list.size() > 0)
            return ServiceResponse.createByErrorMsg("必须先删除所有子项才可删除该项目");
        contentRepository.delete(id);
        return ServiceResponse.createBySuccessMsg("删除成功");
    }
}
