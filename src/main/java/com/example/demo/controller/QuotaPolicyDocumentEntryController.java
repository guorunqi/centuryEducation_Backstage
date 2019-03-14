package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.ControllerReturn;
import com.example.demo.domain.QuotaPolicyDocumentEntryKey;
import com.example.demo.domain.QuotaSelfEvaluationEntryKey;
import com.example.demo.service.QuotaPolicyDocumentEntryService;
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
 * Created by guorunqi on 2019/3/11.
 */
@Controller
public class QuotaPolicyDocumentEntryController {
    @Autowired
    private QuotaPolicyDocumentEntryService quotaPolicyDocumentEntryService;
    @ResponseBody
    @RequestMapping(value = "/saveQuotaPolicyDocumentEntry",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn saveQuotaPolicyDocumentEntry(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String jsonString= JSONObject.parseObject(data).get("data").toString();
            QuotaPolicyDocumentEntryKey quotaPolicyDocumentEntryKey=JSONObject.parseObject(jsonString,QuotaPolicyDocumentEntryKey.class);
            Boolean sign=quotaPolicyDocumentEntryService.insertQuotaPolicyDocumentEntry(quotaPolicyDocumentEntryKey);
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
    @RequestMapping(value = "/deleteQuotaPolicyDocumentEntrys",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn deleteQuotaPolicyDocumentEntrys(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String jsonString=JSONObject.parseObject(data).get("data").toString();
            List<QuotaPolicyDocumentEntryKey> list= JSON.parseArray(jsonString, QuotaPolicyDocumentEntryKey.class);
            Boolean sign=quotaPolicyDocumentEntryService.deleteQuotaPolicyDocumentEntrys(list);
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
    @RequestMapping(value="/queryQuotaPolicyDocumentEntryByQuotaID",method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ControllerReturn queryQuotaPolicyDocumentEntryByQuotaID(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String id=JSONObject.parseObject(data).get("id").toString();
            if(!StringUtils.isBlank(id)) {
                List<HashMap> list = quotaPolicyDocumentEntryService.queryQuotaPolicyDocumentEntryByQuotaID(id);
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
