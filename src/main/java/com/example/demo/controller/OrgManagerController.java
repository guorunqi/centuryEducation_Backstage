package com.example.demo.controller;

import com.example.demo.domain.ControllerReturn;
import com.example.demo.domain.Organization;
import com.example.demo.service.OrgManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by guorunqi on 2019/1/22.
 */
@Controller
public class OrgManagerController {
    @Autowired
    private OrgManagerService orgManagerService;
    @ResponseBody
    @RequestMapping("/queryOrgTree")
    public ControllerReturn queryAllOrganization(){
        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            List<Organization> list=orgManagerService.queryAllOrganization();
            controllerReturn.setCode("true");
            controllerReturn.setData(list);
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
        }
        return controllerReturn;
    }
}
