package com.example.demo.controller;

import com.example.demo.domain.ControllerReturn;
import com.example.demo.domain.Organization;
import com.example.demo.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class OrgController {

    @Autowired
    private OrgService orgServer;

    @ResponseBody
    @RequestMapping(value = "/selectAllOrg")
    public ControllerReturn selectAllOrg(){
        ControllerReturn controllerReturn=new ControllerReturn();
        try {
            List<Organization> selectAllOrg = orgServer.selectAllProject();
            if(selectAllOrg != null){
                controllerReturn.setData(selectAllOrg);
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
