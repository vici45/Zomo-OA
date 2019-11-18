package com.zomo.oa.repository;

import com.zomo.oa.pojo.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectRepositoryTest {
    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void addTest(){
        Project project=new Project();
        String id=UUID.randomUUID().toString();
        project.setId(id);
        project.setName("MSRA XXXX");
        project.setAddress("MSRA大厦");
        project.setCreateUserId(1);
        project.setLeaderId(1);
        project.setCustomerId(9);
        project.setBeginTime(new Date(2014-1900, 6-1, 12));
        project.setEndTime(new Date());
        project.setAccount(new BigDecimal(200));
        project.setTax(new BigDecimal(206));
        project.setTotal(new BigDecimal(206));
        project.setCosts(new BigDecimal(100));
        project.setProjectStatusId(1);

        projectRepository.save(project);
    }

}