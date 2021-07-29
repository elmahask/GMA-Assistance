package com.gma.assistance.gma.repository;

import com.gma.assistance.gma.entity.PatientInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientInsuranceRepository extends JpaRepository<PatientInsurance, Integer> {

}