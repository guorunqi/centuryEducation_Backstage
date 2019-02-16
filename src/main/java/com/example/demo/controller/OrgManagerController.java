package com.example.demo.controller;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.ControllerReturn;
import com.example.demo.domain.OrgType;
import com.example.demo.domain.Organization;
import com.example.demo.service.OrgManagerService;
import com.example.demo.util.TreePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "/saveOrg",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn saveOrganization(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String jsonString=JSONObject.parseObject(data).get("data").toString();
            Organization org=JSONObject.parseObject(jsonString,Organization.class);
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
    @ResponseBody
    @RequestMapping(value="queryOrgById",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn queryOrgById(@RequestBody String data){
        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String id=JSONObject.parseObject(data).get("id").toString();
            boolean sign=false;
            if(id!=null&&!id.equals("")) {
                Organization org = orgManagerService.queryOrgById(id);
                if(org!=null){
                    controllerReturn.setCode("true");
                    controllerReturn.setData(org);
                    sign=true;
                }
            }
            if(!sign){
                controllerReturn.setCode("false");
                controllerReturn.setMessage("主键为空");
            }
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            e.printStackTrace();
        }
        return controllerReturn;
    }
    @ResponseBody
    @RequestMapping(value="updateOrg",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn updateOrg(@RequestBody String data){
        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String jsonString=JSONObject.parseObject(data).get("data").toString();
            Organization org=JSONObject.parseObject(jsonString,Organization.class);
            Boolean sign=orgManagerService.updateOrg(org);
            controllerReturn.setCode("true");
            controllerReturn.setData(sign);
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            e.printStackTrace();
        }
        return controllerReturn;
    }
    @ResponseBody
    @RequestMapping(value="deleteOrg",method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ControllerReturn deleteOrg(@RequestBody String data){
        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String ids=JSONObject.parseObject(data).get("ids").toString();
            String[] idArry=ids.split(",");
            if(idArry!=null&&idArry.length>0){
                for(String id:idArry){
                    if(id!=null&&!id.equals("")) {
                        orgManagerService.deleteOrg(id);
                    }
                }
            }
            controllerReturn.setCode("true");
            controllerReturn.setData("true");
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            e.printStackTrace();
        }
        return controllerReturn;
    }
    @ResponseBody
    @RequestMapping(value="queryAllOrgType",method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ControllerReturn queryAllOrgType(){
        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            List<OrgType> list=orgManagerService.queryOrgType();
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
