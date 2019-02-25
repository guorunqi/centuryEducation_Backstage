package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.ControllerReturn;
import com.example.demo.domain.SelfEvaluationEntryResult;
import com.example.demo.service.SelfEvaluationEntryResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by guorunqi on 2019/2/23.
 */
@Controller
public class SelfEvaluationEntryResultController {
    @Autowired
    private SelfEvaluationEntryResultService selfEvaluationEntryResultService;
    @ResponseBody
    @RequestMapping(value = "/saveSelfEvaluationEntryResult",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn saveSelfEvaluationEntryResult(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String jsonString= JSONObject.parseObject(data).get("data").toString();
            SelfEvaluationEntryResult selfEvaluationEntryResult=JSONObject.parseObject(jsonString,SelfEvaluationEntryResult.class);
            Boolean sign=selfEvaluationEntryResultService.saveSelfEvaluationEntryResult(selfEvaluationEntryResult);
            controllerReturn.setCode(sign.toString());
            controllerReturn.setData(sign);
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            e.printStackTrace();
        }
        return controllerReturn;
    }
}
