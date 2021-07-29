package com.gma.assistance.gma.repository;

import com.gma.assistance.gma.entity.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Integer> {

    Operator findByFirstName(String userName);
    Operator findByEmail(String email);

}