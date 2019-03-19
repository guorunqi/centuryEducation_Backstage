package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.GradeMapper;
import com.example.demo.domain.Assessment;
import com.example.demo.domain.ControllerReturn;
import com.example.demo.domain.Grade;
import com.example.demo.service.AssessmentService;
import com.example.demo.service.GradeService;
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
 * Created by guorunqi on 2019/2/25.
 */
@Controller
public class AssessmentController {
    @Autowired
    private AssessmentService assessmentService;
    @Autowired
    private GradeService gradeService;
    @ResponseBody
    @RequestMapping(value = "/saveAssessment",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn saveAssessment(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String jsonString= JSONObject.parseObject(data).get("data").toString();
            String jsonAssessment= JSONObject.parseObject(jsonString).get("assessment").toString();
            String jsonArade= JSONObject.parseObject(jsonString).get("arades").toString();
            Assessment assessment=JSONObject.parseObject(jsonAssessment,Assessment.class);
            List<Grade> grades= JSON.parseArray(jsonArade,Grade.class);
            String key=null;
            if(StringUtils.isBlank(assessment.getId())){
                key=assessmentService.saveAssessment(assessment,grades);
            }else if(gradeService.deleteGradesByAssessmentID(assessment.getId())){
                key=assessmentService.saveAssessment(assessment,grades);
            }

            if(StringUtils.isBlank(key)){
                controllerReturn.setCode("false");
            }else{
                controllerReturn.setCode("true");
            }

            controllerReturn.setData(key);
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            e.printStackTrace();
        }
        return controllerReturn;
    }

    @ResponseBody
    @RequestMapping(value = "/queryAssessment",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn queryAssessment(String projectName,String name,Integer scoringType){
        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            List<HashMap> list=assessmentService.queryAssessment(projectName,name,scoringType);
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
    @RequestMapping(value = "/deleteAssessments",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn deleteAssessments(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String jsonString=JSONObject.parseObject(data).get("data").toString();
            List<Assessment> list= JSON.parseArray(jsonString,Assessment.class);
            Boolean sign=true;
            if(list.size()>0) {
                sign = assessmentService.deleteAssessments(list);
            }
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
    @RequestMapping(value = "/queryAssessmentByID",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn queryAssessmentByID(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String id= JSONObject.parseObject(data).get("id").toString();
            if(!StringUtils.isBlank(id)){
                Assessment assessment=assessmentService.queryAssessmentByID(id);
                controllerReturn.setCode("true");
                controllerReturn.setData(assessment);
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
    @RequestMapping(value = "/queryGradesByAssessmentID",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn queryGradesByAssessmentID(@RequestBody String data){
        ControllerReturn controllerReturn=new ControllerReturn();
        String id= JSONObject.parseObject(data).get("id").toString();
        try{
            List<Grade> list=gradeService.queryGradeByAssessmentID(id);
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
