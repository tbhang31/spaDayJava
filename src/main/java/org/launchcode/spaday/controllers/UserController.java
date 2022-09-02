package org.launchcode.spaday.controllers;

import org.launchcode.spaday.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("/add")
    public String displayAddUserForm(Model model) {
        model.addAttribute(new User());
        return "user/add";
    }

    @PostMapping("/add")
    public String processAddUserForm(@ModelAttribute @Valid User user, Model model, String verify, Errors error) {
        model.addAttribute(new User());
        model.addAttribute("verify",verify);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email",user.getEmail());
        if (error.hasErrors()) {
            return "user/add";
        } else {
            if (user.getPassword().equals(verify)) {
                return "user/index";
            } else {
                model.addAttribute("error","Passwords do not match");
                return "user/add";
            }
        }
    }
}
