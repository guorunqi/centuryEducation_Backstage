package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.LoginService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;





    public String login(){
        return "login.html";
    }

    @ResponseBody
    @RequestMapping("/login")
    public String loginVerify(String username,String password){

        User user = new User();
        user.setName(username);
        user.setPassword(password);

        boolean verify = loginService.verifyLogin(user);
        if (verify) {
            //session.setAttribute(WebSecurityConfig.SESSION_KEY, username);
            return "true";
        } else {
            return "false";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        //session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "redirect:/login";
    }
}
