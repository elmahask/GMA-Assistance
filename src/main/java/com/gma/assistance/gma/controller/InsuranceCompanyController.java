package com.gma.assistance.gma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class InsuranceCompanyController {
    @GetMapping("insurance-companys")
    public String insuranceCompanys() {
        return "table-insurance";
    }

    @GetMapping("insurance-company")
    public String insuranceCompany() {
        return "add-insurance-company";
    }

    @PostMapping("insurance-company")
    public String addInsuranceCompany() {
        return "add-insurance-company";
    }
}
