package crudapp.crudApp.controller;

import crudapp.crudApp.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import crudapp.crudApp.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/new_user")
    public String showNewUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("userId", null);
        return "add_update_user";
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult result,
                           @RequestParam(required = false) Integer userId, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            if (userId != null) {
                model.addAttribute("userId", userId);
            }
            return "add_update_user";
        }
        if (userId != null) {
            user.setId(userId);
            userService.saveUser(user);
        } else {
            userService.saveUser(user);
        }
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showFormFowUpdate(@PathVariable(value = "id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("userId", id);
        return "add_update_user";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") int id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }
}
