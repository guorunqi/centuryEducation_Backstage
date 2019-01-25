package com.example.demo.controller;

import com.example.demo.domain.ControllerReturn;
import com.example.demo.domain.DictEntry;
import com.example.demo.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CommonController {

    @Autowired
    private CommonService commonService;

    @ResponseBody
    @RequestMapping(value = "/getDictAllByDictTypeId")
    public ControllerReturn getDictAllByDictTypeId(String dictTypeId){
        ControllerReturn controllerReturn=new ControllerReturn();
        try {
            List<DictEntry> getDictAllByDictTypeIdList = commonService.getDictAllByDictTypeId(dictTypeId);
            if(getDictAllByDictTypeIdList != null){
                controllerReturn.setData(getDictAllByDictTypeIdList);
                controllerReturn.setCode("true");
                return controllerReturn;
            }else{
                controllerReturn.setMessage("没有查询到数据 ");
                controllerReturn.setCode("false");
                return controllerReturn;
            }
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }
}
