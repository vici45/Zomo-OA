package com.zomo.oa.repository;

import com.zomo.oa.pojo.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content,Integer> {
    List<Content> findByParent(Integer parent);
}
