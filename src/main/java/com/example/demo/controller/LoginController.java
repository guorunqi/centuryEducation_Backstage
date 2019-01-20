package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;



    @RequestMapping(value = "/login")
    @ResponseBody
    public String loginVerify(String username,String password,HttpSession session){

        User user = loginService.loginVerification(username);
        if (user!=null&&password.equals(user.getPassword())) {
            session.setAttribute("user", username);
            return "true";
        } else {
            return "false";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/login";
    }
}
