package org.example.controller;

import org.example.domain.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model){
        if (user.getPassword() != null && !user.getPassword().equals(user.getPassword2())){                     //password1 != password2
            model.addAttribute("passwordError", "Passwords are different!");
        }
        if (bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);

            return "registration";
        }
        if (!userService.addUser(user)){                                //если не могли добавить user
            model.addAttribute("usernameError", "User exists!");

            return "registration";
        }

        return "redirect:/login";                    //при успешной авторизации делаем редирект на логин
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);
        if (isActivated){
            model.addAttribute("message","User successfully activated");
        } else {
            model.addAttribute("message","Activation code is not found");
        }
        return "login";
    }
}
