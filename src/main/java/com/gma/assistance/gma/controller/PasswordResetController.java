package com.gma.assistance.gma.controller;

import com.gma.assistance.gma.exception.InvalidTokenException;
import com.gma.assistance.gma.exception.UnkownIdentifierException;
import com.gma.assistance.gma.model.ResetPasswordData;
import com.gma.assistance.gma.service.IOperatorAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;

@Controller
@RequestMapping("/password")
public class PasswordResetController {

    private static final String REDIRECT_LOGIN = "redirect:/login";
    private static final String MSG = "resetPasswordMsg";

    @Autowired
    private MessageSource messageSource;
    //    @Autowired
//    private OperatorService userService;
    @Autowired
    private IOperatorAccountService customerAccountService;


    @GetMapping("/reset")
    public String reset(final Model model) {
        model.addAttribute("resetPassword", new ResetPasswordData());
        return "auth-forgot-password";
    }

    @PostMapping("/reset")
    public String userRegistration(@Valid ResetPasswordData resetPassword, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("resetPassword", resetPassword);
            return "auth-forgot-password";
        }
        if (resetPassword != null && resetPassword.getEmail().isEmpty()) {
            bindingResult.addError(new FieldError("resetPassword", "email", "We could not find an account for that e-mail address."));
            redirectAttributes.addFlashAttribute("resetPasswordMsg", messageSource.getMessage("user.forgotpwd.msg", null, LocaleContextHolder.getLocale()));
        } else {

            try {
                customerAccountService.forgottenPassword(resetPassword.getEmail());
            } catch (UnkownIdentifierException e) {
                e.printStackTrace();
//                bindingResult.rejectValue("resetPasswordMsg", null, "We could not find an account for that e-mail address.");
            }
        }
        redirectAttributes.addFlashAttribute("resetPasswordMsg", messageSource.getMessage("user.forgotpwd.msg", null, LocaleContextHolder.getLocale())
        );
        return "redirect:/password/reset";
    }

    @GetMapping("/change")
    public String changePassword(@RequestParam String token, final RedirectAttributes redirAttr, final Model model) {
        if (StringUtils.isEmpty(token)) {
            redirAttr.addFlashAttribute("tokenError",
                    messageSource.getMessage("user.registration.verification.missing.token", null, LocaleContextHolder.getLocale())
            );
            return "auth-reset-password";
        }

        ResetPasswordData data = new ResetPasswordData();
        data.setToken(token);
        setResetPasswordForm(model, data);

        return "auth-reset-password";
    }

    @PostMapping("/change")
    public String changePassword(final ResetPasswordData data, final Model model) {
        try {
            customerAccountService.updatePassword(data.getPassword(), data.getToken());
        } catch (InvalidTokenException | UnkownIdentifierException e) {
            // log error statement
            model.addAttribute("tokenError",
                    messageSource.getMessage("user.registration.verification.invalid.token", null, LocaleContextHolder.getLocale())
            );

            return "auth-reset-password";
        }
        model.addAttribute("passwordUpdateMsg",
                messageSource.getMessage("user.password.updated.msg", null, LocaleContextHolder.getLocale())
        );
        setResetPasswordForm(model, new ResetPasswordData());
        return "redirect:/login?reset";
    }

    private void setResetPasswordForm(final Model model, ResetPasswordData data) {
        model.addAttribute("forgotPassword", data);
    }
}