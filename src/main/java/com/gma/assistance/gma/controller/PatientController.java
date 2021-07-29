package com.gma.assistance.gma.controller;
import com.gma.assistance.gma.entity.Country;
import com.gma.assistance.gma.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

//@RestController
//@RequestMapping("patient")
@Controller
public class PatientController {
    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("v1")
    public List<Country> country() {
        // After user login successfully.
        //String userName = principal.getName();
//        System.out.println("User Name: " + userName+" "+securityService.isAuthenticated());
        //model.addAttribute("profile", false);

        return countryRepository.findAll();
    }

    @GetMapping("patient")
    public String patient() {
        // After user login successfully.
        //String userName = principal.getName();
//        System.out.println("User Name: " + userName+" "+securityService.isAuthenticated());
        //model.addAttribute("profile", false);

        return "add-patient";
    }

    @GetMapping("patients")
    public String patients() {
        // After user login successfully.
        //String userName = principal.getName();
//        System.out.println("User Name: " + userName+" "+securityService.isAuthenticated());
        //model.addAttribute("profile", false);

        return "table-patient";
    }


    @PostMapping("patient")
    public String addPatient() {
        // After user login successfully.
        //String userName = principal.getName();
//        System.out.println("User Name: " + userName+" "+securityService.isAuthenticated());
        //model.addAttribute("profile", false);
        return "add-patient";
    }

}
