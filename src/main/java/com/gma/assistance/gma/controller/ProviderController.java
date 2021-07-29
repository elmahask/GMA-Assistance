package com.gma.assistance.gma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class ProviderController {

    @GetMapping("provider")
    public String provider() {
        // After user login successfully.
        //String userName = principal.getName();
//        System.out.println("User Name: " + userName+" "+securityService.isAuthenticated());
        //model.addAttribute("profile", false);

        return "add-provider";
    }

    @GetMapping("providers")
    public String providers() {
        // After user login successfully.
        //String userName = principal.getName();
//        System.out.println("User Name: " + userName+" "+securityService.isAuthenticated());
        //model.addAttribute("profile", false);

        return "table-provider";
    }


    @PostMapping("provider")
    public String addProvider() {
        // After user login successfully.
        //String userName = principal.getName();
//        System.out.println("User Name: " + userName+" "+securityService.isAuthenticated());
        //model.addAttribute("profile", false);
        return "add-provider";
    }
}
