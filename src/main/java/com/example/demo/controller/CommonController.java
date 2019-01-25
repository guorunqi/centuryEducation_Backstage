package com.example.demo.controller;
import com.example.demo.domain.ControllerReturn;
import com.example.demo.domain.DictEntry;
import com.example.demo.domain.Organization;
import com.example.demo.domain.OrganizationExample;
import com.example.demo.service.CommonService;
import com.example.demo.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class CommonController {

    @Autowired
    private CommonService commonService;
    @Autowired
    private OrgService orgService;

    @ResponseBody
    @RequestMapping(value = "/getDictAllByDictTypeId")
    public ControllerReturn getDictAllByDictTypeId(String dictTypeId){
        ControllerReturn controllerReturn=new ControllerReturn();
        try {
            List<DictEntry> getDictAllByDictTypeIdList = commonService.getDictAllByDictTypeId(dictTypeId);
            if(getDictAllByDictTypeIdList.size() > 0){
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

    @ResponseBody
    @RequestMapping(value = "/selectSchoolListByIdList",method = {RequestMethod.POST})
    public ControllerReturn selectSchoolListByIdList(@RequestBody Map codeArr) {
        ControllerReturn controllerReturn = new ControllerReturn();
        OrganizationExample organizationExample = new OrganizationExample();
        List<Organization> list = new ArrayList<>();
        try {
            Iterator it = codeArr.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entity = (Map.Entry) it.next();
                String key= (String) entity.getKey();
                if (key == "IdList"){
                    ArrayList lists = (ArrayList) entity.getValue();
                    for (int i = 0; i < lists.size(); i++) {
                        Organization o = orgService.selectOrgBycode(lists.get(i).toString());
                      if(o != null){
                          list.add(o);
                      }
                    }
                }
            }
            if(list.size() > 0){
                controllerReturn.setData(list);
                controllerReturn.setCode("true");
                return controllerReturn;
            }
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
        return controllerReturn;
        }
    }

