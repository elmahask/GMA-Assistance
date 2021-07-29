package com.gma.assistance.gma.repository;

import com.gma.assistance.gma.entity.InsuranceCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceCompanyRepository extends JpaRepository<InsuranceCompany, Integer>{

}