package com.zomo.oa.service.imp;

import com.zomo.oa.repository.ProjectPersonnelRepository;
import com.zomo.oa.service.ProjectPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectPersonnelServiceImp implements ProjectPersonnelService {
    @Autowired
    private ProjectPersonnelRepository projectPersonnelRepository;
}
