package com.gma.assistance.gma.controller;

import com.gma.assistance.gma.entity.Operator;
import com.gma.assistance.gma.exception.InvalidTokenException;
import com.gma.assistance.gma.exception.UserAlreadyExistException;
import com.gma.assistance.gma.service.IOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;

@Controller
public class RegisterController {
    @Autowired
    private IOperatorService userService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/register")
    public String register(final Model model) {
        model.addAttribute("operator", new Operator());
        return "auth-register";
    }

    @PostMapping("/register")
    public String userRegistration(@Valid Operator operator, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        Operator user = null;
        if (bindingResult.hasErrors()) {
            model.addAttribute("operator", operator);
            return "auth-register";
        }
        try {
            user = userService.registerOperator(operator);
        } catch (UserAlreadyExistException e) {
            bindingResult.rejectValue("email", "operator.email", "An account already exists for this email.");
            model.addAttribute("registrationForm", operator);
            return "auth-register";
        }
        redirectAttributes.addFlashAttribute("output", user.getId());
        model.addAttribute("registrationMsg", messageSource.getMessage("user.registration.verification.email.msg", null, LocaleContextHolder.getLocale()));
        return "redirect:/register";
    }

    @GetMapping("/verify")
    public String verifyOperator(@RequestParam(required = false) String token, final Model model, RedirectAttributes redirectAttributes) {
        if (StringUtils.isEmpty(token)) {
            redirectAttributes.addFlashAttribute("tokenError", messageSource.getMessage("user.registration.verification.missing.token", null, LocaleContextHolder.getLocale()));
            return "redirect:/login";
        }
        try {
            userService.verifyUser(token);
        } catch (InvalidTokenException e) {
            redirectAttributes.addFlashAttribute("tokenError", messageSource.getMessage("user.registration.verification.invalid.token", null, LocaleContextHolder.getLocale()));
            return "redirect:/login";
        }

//        redirectAttributes.addFlashAttribute("logout", messageSource.getMessage("user.registration.verification.success", null, LocaleContextHolder.getLocale()));
        redirectAttributes.addFlashAttribute("verifiedAccountMsg", messageSource.getMessage("user.registration.verification.success", null, LocaleContextHolder.getLocale()));
        return "redirect:/login";
    }
}
