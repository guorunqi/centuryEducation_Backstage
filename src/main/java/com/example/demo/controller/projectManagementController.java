package com.example.demo.controller;

import com.example.demo.domain.ControllerReturn;
import com.example.demo.domain.Project;
import com.example.demo.service.projectManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class projectManagementController {
    @Autowired
    private projectManagementService loginService;

    @RequestMapping(value = "/projectManagementLoad")
    @ResponseBody
    public ControllerReturn loginVeify(){
        ControllerReturn controllerReturn=new ControllerReturn();
        try {
            List<Project> projectList = loginService.selectAllProject();
            if(projectList != null){
                controllerReturn.setData(projectList);
                controllerReturn.setCode("true");
                return controllerReturn;
            }else{
                controllerReturn.setMessage("没有查询到数据 ");
                controllerReturn.setCode("false");
                return controllerReturn;
            }

        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }
}
