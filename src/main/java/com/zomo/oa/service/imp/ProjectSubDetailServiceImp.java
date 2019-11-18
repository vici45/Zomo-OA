package com.zomo.oa.service.imp;

import com.zomo.oa.repository.ProjectSubDetailRepository;
import com.zomo.oa.service.ProjectSubDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectSubDetailServiceImp implements ProjectSubDetailService {
    @Autowired
    private ProjectSubDetailRepository projectSubDetailRepository;
}
