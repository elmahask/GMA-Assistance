package com.gma.assistance.gma.repository;

import com.gma.assistance.gma.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

//    List<Role> findAllByOperatorId(Integer operatorId);
}