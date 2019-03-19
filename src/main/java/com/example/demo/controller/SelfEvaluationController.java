package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.ControllerReturn;
import com.example.demo.domain.SelfEvaluation;
import com.example.demo.domain.User;
import com.example.demo.service.SelfEvaluationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by guorunqi on 2019/2/17.
 */
@Controller
public class SelfEvaluationController {
    @Autowired
    private SelfEvaluationService selfEvaluationService;
    @ResponseBody
    @RequestMapping(value = "/querySelfEvaluation",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn querySelfEvaluation(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String json= JSONObject.parseObject(data).get("data").toString();
            String name= JSONObject.parseObject(json).get("name").toString();
            String projectName= JSONObject.parseObject(json).get("projectName").toString();
            HashMap map=new HashMap();
            map.put("name",name);
            map.put("projectName",projectName);
            List<SelfEvaluation> list=selfEvaluationService.querySelfEvaluation(map);
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
    @RequestMapping(value = "/querySelfEvaluationByID",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn querySelfEvaluationByID(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String id= JSONObject.parseObject(data).get("id").toString();
            if(!StringUtils.isBlank(id)){
                SelfEvaluation selfEvaluation=selfEvaluationService.querySelfEvaluationById(id);
                controllerReturn.setCode("true");
                controllerReturn.setData(selfEvaluation);
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
    @RequestMapping(value = "/saveSelfEvaluation",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn saveSelfEvaluation(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String jsonString=JSONObject.parseObject(data).get("data").toString();
            SelfEvaluation selfEvaluation=JSONObject.parseObject(jsonString,SelfEvaluation.class);
            String key=selfEvaluationService.saveSelfEvaluation(selfEvaluation);
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
    @RequestMapping(value="/deleteSelfEvaluation",method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ControllerReturn deleteSelfEvaluation(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String jsonString=JSONObject.parseObject(data).get("data").toString();
            List<SelfEvaluation> selfEvaluations= JSON.parseArray(jsonString,SelfEvaluation.class);
            Boolean sign=selfEvaluationService.deleteSelfEvaluations(selfEvaluations);
            controllerReturn.setCode("true");
            controllerReturn.setData(sign);
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            e.printStackTrace();
        }
        return controllerReturn;
    }
    //评估条目关联自评条目获取条目
    @ResponseBody
    @RequestMapping(value="/querySelfEvaluationDataByID",method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ControllerReturn querySelfEvaluationDataByID(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String id=JSONObject.parseObject(data).get("id").toString();

            List<HashMap> list=selfEvaluationService.querySelfEvaluationDataByID(id);
            controllerReturn.setCode("true");
            controllerReturn.setData(list);
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            e.printStackTrace();
        }
        return controllerReturn;
    }
    //评估条目关联政策文件获取条目
    @ResponseBody
    @RequestMapping(value="/queryPolicyDocumentEntryByID",method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ControllerReturn queryPolicyDocumentEntryByID(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String id=JSONObject.parseObject(data).get("id").toString();

            List<HashMap> list=selfEvaluationService.queryPolicyDocumentEntryByID(id);
            controllerReturn.setCode("true");
            controllerReturn.setData(list);
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            e.printStackTrace();
        }
        return controllerReturn;
    }
    //评估条目关联问卷问题获取条目
    @ResponseBody
    @RequestMapping(value="/queryProblemByID",method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ControllerReturn queryProblemByID(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String id=JSONObject.parseObject(data).get("id").toString();

            List<HashMap> list=selfEvaluationService.queryProblemByID(id);
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
