package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.ControllerReturn;
import com.example.demo.domain.QuotaProblemKey;
import com.example.demo.service.QuotaProblemService;
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
public class QuotaProblemController {
    @Autowired
    private QuotaProblemService quotaProblemService;
    @ResponseBody
    @RequestMapping(value = "/saveQuotaProblem",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn saveQuotaProblem(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String jsonString= JSONObject.parseObject(data).get("data").toString();
            QuotaProblemKey quotaProblemKey=JSONObject.parseObject(jsonString,QuotaProblemKey.class);
            Boolean sign=quotaProblemService.insertQuotaProblem(quotaProblemKey);
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
    @RequestMapping(value = "/deleteQuotaProblems",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn deleteQuotaProblems(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String jsonString=JSONObject.parseObject(data).get("data").toString();
            List<QuotaProblemKey> list= JSON.parseArray(jsonString, QuotaProblemKey.class);
            Boolean sign=quotaProblemService.deleteQuotaProblems(list);
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
    @RequestMapping(value="/queryQuotaProblemByQuotaID",method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ControllerReturn queryQuotaProblemByQuotaID(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String id=JSONObject.parseObject(data).get("id").toString();
            if(!StringUtils.isBlank(id)) {
                List<HashMap> list = quotaProblemService.queryQuotaProblemByQuotaID(id);
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
