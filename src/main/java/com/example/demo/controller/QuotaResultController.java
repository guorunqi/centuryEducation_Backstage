package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.ControllerReturn;
import com.example.demo.domain.QuotaResult;
import com.example.demo.domain.QuotaResultWithBLOBs;
import com.example.demo.service.QuotaResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by guorunqi on 2019/3/20.
 */
@Controller
public class QuotaResultController {
    @Autowired
    private QuotaResultService quotaResultService;
    @ResponseBody
    @RequestMapping(value = "/saveQuotaResults",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn saveQuotaResults(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String jsonString= JSONObject.parseObject(data).get("data").toString();
            List<QuotaResultWithBLOBs> list= JSONArray.parseArray(jsonString,QuotaResultWithBLOBs.class);
            if(list.size()>0){
                Boolean sign=quotaResultService.saveQuotaResults(list);
                controllerReturn.setCode(sign.toString());
                controllerReturn.setData(list);
            }else{
                controllerReturn.setCode("false");
                controllerReturn.setData("参数不正确");
            }
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            e.printStackTrace();
        }
        return controllerReturn;
    }
}
