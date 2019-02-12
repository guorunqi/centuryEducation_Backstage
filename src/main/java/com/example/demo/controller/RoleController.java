package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.ControllerReturn;
import com.example.demo.domain.Role;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by guorunqi on 2019/2/12.
 */
@Controller
public class RoleController {
    @Autowired
    RoleService roleService;
    @ResponseBody
    @RequestMapping(value = "/queryRole",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn queryUsersByOrgID(){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            List<Role> list=roleService.queryAllRole();
            controllerReturn.setCode("true");
            controllerReturn.setData(list);

        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            e.printStackTrace();
        }
        return controllerReturn;
    }
}
