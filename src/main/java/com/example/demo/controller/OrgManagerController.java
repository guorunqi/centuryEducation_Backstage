package com.example.demo.controller;

import com.example.demo.domain.ControllerReturn;
import com.example.demo.domain.Organization;
import com.example.demo.service.OrgManagerService;
import com.example.demo.util.TreePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
            List<TreePojo> list=orgManagerService.queryAllOrganization();
            controllerReturn.setCode("true");
            controllerReturn.setData(list);
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            e.printStackTrace();
        }
        return controllerReturn;
    }
    @ResponseBody
    @RequestMapping("/saveOrg")
    public ControllerReturn saveOrganization(@RequestBody Organization org){
        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            Boolean sign=orgManagerService.saveOrganization(org);
            controllerReturn.setCode("true");
            controllerReturn.setData(sign);
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            e.printStackTrace();
        }
        return controllerReturn;
    }
}
