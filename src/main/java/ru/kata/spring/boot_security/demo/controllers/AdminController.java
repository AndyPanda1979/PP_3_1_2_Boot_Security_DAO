package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.security.UserDetailsImpl;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.util.List;

@Controller
public class AdminController {

    final UserServiceImpl userService;
    final RoleService roleService;

    public AdminController(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin")
    public String adminPage(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User admin = userDetails.getUser();
        model.addAttribute("admin", admin);
        return ("admin");
    }

    /* ====== READ ====== */

    @GetMapping(value = "admin/users")
    public String allUsers(ModelMap model) {
        List<User> users = userService.getUsers();
        model.addAttribute("usersList", users);
        return ("admin/users");
    }

    @GetMapping(value = "/admin/user/{id}")
    public String userDetails(@PathVariable ("id") Long id, ModelMap model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return ("admin/user");
    }


    /* ====== CREATE ====== */

    @GetMapping(value = "/admin/user/add")
    public String addUser(@ModelAttribute ("user") User user, ModelMap model) {
        return ("admin/adduser_form");
    }

    @PostMapping(value = "/admin/user/add")
    public String createUser(@ModelAttribute ("user") User user) {
        user.addRole(roleService.getRoleByName("ROLE_USER"));
        userService.saveUser(user);
        return ("redirect:/admin/users");
    }

    /* ====== UPDATE ====== */

    @GetMapping(value = "admin/user/{id}/edit")
    public String editUser(@PathVariable ("id") Long id, ModelMap model) {
        User user = userService.getUserById(id);
        List<String> userRoles = userService.getUserRolesAsStringList(user);
        List<Role> listRoles = roleService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("userRoles", userRoles);
        // разбираемся с check

        boolean flag = true;
        model.addAttribute("flag", flag);
        return ("admin/edituser_form");
    }

    @PatchMapping("admin/user")
    public String saveUser(@ModelAttribute ("user") User user) {
        userService.updateUser(user);
        return ("redirect:/admin/users");
    }

    /* ====== DELETE ====== */

    @DeleteMapping(value = "admin/user/{id}/delete")
    public String deleteUser(@PathVariable ("id") long id, ModelMap model) {
        User user = userService.getUserById(id);
        userService.deleteUser(user);
        return ("redirect:/admin/users");
    }

}
