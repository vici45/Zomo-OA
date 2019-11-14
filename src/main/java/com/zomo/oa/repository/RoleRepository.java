package com.zomo.oa.repository;

import com.zomo.oa.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    List<Role> findByPositionLike(String position);
}
