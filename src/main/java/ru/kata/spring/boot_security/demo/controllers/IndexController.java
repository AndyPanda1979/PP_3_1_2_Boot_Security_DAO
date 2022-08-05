package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;


@Controller
public class IndexController {


    @GetMapping(value = "/")
    public String indexPage(ModelMap model, @AuthenticationPrincipal User testUser) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication.getPrincipal().toString().contains("anonymousUser")) {
        if (testUser == null) {
            model.addAttribute("userName", "Guest");
            model.addAttribute("authenticated", false);
        } else {
//            User userDetails = (User) authentication.getPrincipal();
//            model.addAttribute("userName", userDetails.getUsername());
//            model.addAttribute("userDetails", userDetails);
            model.addAttribute("userName", testUser.getFirstName() + " " + testUser.getLastName());
            model.addAttribute("userDetails", testUser);
            model.addAttribute("authenticated", true);
        }
        return ("index");
    }
}
