package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.ControllerReturn;
import com.example.demo.domain.Organization;
import com.example.demo.domain.ProjectOrg;
import com.example.demo.domain.SelfEvaluationEntry;
import com.example.demo.service.OrgService;
import com.example.demo.service.ProjectOrgService;
import com.example.demo.service.SelfEvaluationEntryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guorunqi on 2019/2/20.
 */
@Controller
public class SelfEvaluationEntryController {
    @Autowired
    private SelfEvaluationEntryService selfEvaluationEntryService;
    @Autowired
    private OrgService orgService;
    @ResponseBody
    @RequestMapping(value = "/queryAllSelfEvaluationEntry",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn queryAllSelfEvaluationEntry(){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            List<SelfEvaluationEntry> list=selfEvaluationEntryService.queryAllSelfEvaluationEntry();
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
    @RequestMapping(value = "/querySelfEvaluationEntryBySelfEvaluationID",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn querySelfEvaluationEntryBySelfEvaluationID(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String id= JSONObject.parseObject(data).get("id").toString();
            if(!StringUtils.isBlank(id)){
                List<SelfEvaluationEntry> selfEvaluationEntrys=selfEvaluationEntryService.querySelfEvaluationEntryBySelfEvaluationID(id);
                controllerReturn.setCode("true");
                controllerReturn.setData(selfEvaluationEntrys);
            }else{
                controllerReturn.setCode("false");
                controllerReturn.setData("id参数不正确");
            }
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            e.printStackTrace();
        }
        return controllerReturn;
    }
    @ResponseBody
    @RequestMapping(value = "/querySelfEvaluationEntryByID",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn querySelfEvaluationEntryByID(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String id= JSONObject.parseObject(data).get("id").toString();
            if(!StringUtils.isBlank(id)){
                SelfEvaluationEntry selfEvaluationEntry=selfEvaluationEntryService.querySelfEvaluationEntryById(id);
                controllerReturn.setCode("true");
                controllerReturn.setData(selfEvaluationEntry);
            }else{
                controllerReturn.setCode("false");
                controllerReturn.setData("id参数不正确");
            }
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            e.printStackTrace();
        }
        return controllerReturn;
    }
    @ResponseBody
    @RequestMapping(value = "/saveSelfEvaluationEntry",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn saveSelfEvaluationEntry(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String jsonString=JSONObject.parseObject(data).get("data").toString();
            SelfEvaluationEntry selfEvaluationEntry=JSONObject.parseObject(jsonString,SelfEvaluationEntry.class);
            String key=selfEvaluationEntryService.saveSelfEvaluation(selfEvaluationEntry);
            controllerReturn.setCode("true");
            controllerReturn.setData(key);
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            e.printStackTrace();
        }
        return controllerReturn;
    }
    @ResponseBody
    @RequestMapping(value="/deleteSelfEvaluationEntrys",method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ControllerReturn deleteSelfEvaluationEntrys(@RequestBody String data){
        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String jsonString=JSONObject.parseObject(data).get("data").toString();
            List<SelfEvaluationEntry> selfEvaluationEntrys= JSON.parseArray(jsonString,SelfEvaluationEntry.class);
            Boolean sign=selfEvaluationEntryService.deleteSelfEvaluationEntrys(selfEvaluationEntrys);
            controllerReturn.setCode(sign.toString());
            controllerReturn.setData(sign);
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            e.printStackTrace();
        }
        return controllerReturn;
    }
    @ResponseBody
    @RequestMapping(value = "/SelfEvaluationEntry/queryOrgByProjectID",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn queryOrgByProjectID(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String id= JSONObject.parseObject(data).get("id").toString();
            if(!StringUtils.isBlank(id)){
                List<Organization> list=orgService.queryOrgByProjectID(id);
                controllerReturn.setCode("true");
                controllerReturn.setData(list);
            }else{
                controllerReturn.setCode("false");
                controllerReturn.setData("id参数不正确");
            }
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            e.printStackTrace();
        }
        return controllerReturn;
    }
}
