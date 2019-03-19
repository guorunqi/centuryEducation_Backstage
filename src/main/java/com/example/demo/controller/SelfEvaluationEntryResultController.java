package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.ControllerReturn;
import com.example.demo.domain.ProjectOrg;
import com.example.demo.domain.SelfEvaluationEntryResult;
import com.example.demo.service.ProjectOrgService;
import com.example.demo.service.SelfEvaluationEntryResultService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * Created by guorunqi on 2019/2/23.
 */
@Controller
public class SelfEvaluationEntryResultController {
    @Autowired
    private SelfEvaluationEntryResultService selfEvaluationEntryResultService;
    @Autowired
    private ProjectOrgService projectOrgService;
    @ResponseBody
    @RequestMapping(value = "/saveSelfEvaluationEntryResult",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn saveSelfEvaluationEntryResult(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String jsonString= JSONObject.parseObject(data).get("data").toString();
            SelfEvaluationEntryResult selfEvaluationEntryResult=JSONObject.parseObject(jsonString,SelfEvaluationEntryResult.class);
            SelfEvaluationEntryResult selfEvaluationEntryResult1=selfEvaluationEntryResultService.saveSelfEvaluationEntryResult(selfEvaluationEntryResult);
            if(selfEvaluationEntryResult1==null){
                controllerReturn.setCode("false");
            }else{
                controllerReturn.setCode("true");
            }
            controllerReturn.setData(selfEvaluationEntryResult1);

        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            e.printStackTrace();
        }
        return controllerReturn;
    }
    @ResponseBody
    @RequestMapping(value = "/querySelfEvaluationEntryResultByOrgID",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn querySelfEvaluationEntryResultByOrgID(@RequestBody String data){
        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String jsonString= JSONObject.parseObject(data).get("data").toString();
            String selfEvaluationEntryId=JSONObject.parseObject(jsonString).get("selfEvaluationEntryId").toString();
            String projectOrgId=JSONObject.parseObject(jsonString).get("projectOrgId").toString();
            if(!StringUtils.isBlank(selfEvaluationEntryId)&&!StringUtils.isBlank(projectOrgId)) {
                SelfEvaluationEntryResult selfEvaluationEntryResult = selfEvaluationEntryResultService.querySelfEvaluationEntryResultByOrgID(selfEvaluationEntryId, projectOrgId);
                controllerReturn.setCode("true");
                if(selfEvaluationEntryResult==null){
                    selfEvaluationEntryResult=new SelfEvaluationEntryResult();
                    selfEvaluationEntryResult.setProjectOrgId(projectOrgId);
                    selfEvaluationEntryResult.setSelfEvaluationEntryId(selfEvaluationEntryId);
                }
                controllerReturn.setData(selfEvaluationEntryResult);
            }else{
                controllerReturn.setCode("false");
                controllerReturn.setData("参数传输不正确");
            }
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            e.printStackTrace();
        }
        return controllerReturn;
    }
    @ResponseBody
    @RequestMapping(value = "/SelfEvaluationEntryResult/queryProjectOrgByProjectID",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn queryProjectOrgByProjectID(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String id= JSONObject.parseObject(data).get("id").toString();
            if(!StringUtils.isBlank(id)){
                List<HashMap> list=projectOrgService.queryProjectOrgByProjectID(id);
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
    @ResponseBody
    @RequestMapping(value = "/SelfEvaluationEntryResult/queryProjectOrgByProjectIDAndQuotaID",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn queryProjectOrgByProjectIDAndQuotaID(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String projectID= JSONObject.parseObject(data).get("projectID").toString();
            String quotaID= JSONObject.parseObject(data).get("quotaID").toString();
            if(!StringUtils.isBlank(projectID)){
                List<HashMap> list=projectOrgService.queryProjectOrgByProjectIDAndQuotaID(projectID,quotaID);
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
