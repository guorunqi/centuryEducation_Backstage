package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.ControllerReturn;
import com.example.demo.domain.Quota;
import com.example.demo.domain.QuotaSelfEvaluationEntryKey;
import com.example.demo.service.QuotaSelfEvaluationEntryService;
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
 * Created by guorunqi on 2019/3/8.
 */
@Controller
public class QuotaSelfEvaluationEntryController {
    @Autowired
    private QuotaSelfEvaluationEntryService quotaSelfEvaluationEntryService;
    @ResponseBody
    @RequestMapping(value = "/saveQuotaSelfEvaluationEntry",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn saveQuotaSelfEvaluationEntry(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String jsonString=JSONObject.parseObject(data).get("data").toString();
            QuotaSelfEvaluationEntryKey quotaSelfEvaluationEntryKey=JSONObject.parseObject(jsonString,QuotaSelfEvaluationEntryKey.class);
            Boolean sign=quotaSelfEvaluationEntryService.insertQuotaSelfEvaluationEntrys(quotaSelfEvaluationEntryKey);
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
    @RequestMapping(value = "/deleteQuotaSelfEvaluationEntrys",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn deleteQuotaSelfEvaluationEntrys(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String jsonString=JSONObject.parseObject(data).get("data").toString();
            List<QuotaSelfEvaluationEntryKey> list= JSON.parseArray(jsonString, QuotaSelfEvaluationEntryKey.class);
            Boolean sign=quotaSelfEvaluationEntryService.deleteQuotaSelfEvaluationEntry(list);
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
    @RequestMapping(value="/queryQuotaSelfEvaluationEntryByQuotaID",method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ControllerReturn queryQuotaSelfEvaluationEntryByQuotaID(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String id=JSONObject.parseObject(data).get("id").toString();
            if(!StringUtils.isBlank(id)) {
                List<HashMap> list = quotaSelfEvaluationEntryService.queryQuotaSelfEvaluationEntryByQuotaID(id);
                controllerReturn.setCode("true");
                controllerReturn.setData(list);
            }else {
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
