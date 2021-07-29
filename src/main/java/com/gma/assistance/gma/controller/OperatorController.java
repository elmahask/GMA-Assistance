package com.gma.assistance.gma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
//@RequestMapping("/administration")
public class OperatorController {
    @GetMapping("/administration")
    public String profile(Model model, Principal principal) {
        // After user login successfully.
        //String userName = principal.getName();
//        System.out.println("User Name: " + userName+" "+securityService.isAuthenticated());
        //model.addAttribute("profile", false);

        return "profile/index";
    }



}
