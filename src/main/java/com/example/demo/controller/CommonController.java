package com.example.demo.controller;
import com.example.demo.domain.*;
import com.example.demo.service.CommonService;
import com.example.demo.service.OrgService;
import com.example.demo.service.PolicyDocumentService;
import com.example.demo.service.UserService;
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
    @Autowired
    private UserService userService;
    @Autowired
    private PolicyDocumentService policyDocumentService;

    /**
     * 根据业务字典ID，查询业务字典信息
     * @param dictTypeId
     * @return
     */
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

    /**
     * 根据机构id[] 查询所对应的机构
     * @param codeArr
     * @return
     */
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

    /**
     * 查询所有政策文件
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectAllSpecialist")
    public ControllerReturn selectAllSpecialist() {
        ControllerReturn controllerReturn = new ControllerReturn();
        try {
            List<User> userList = userService.selectAllSpecialist();
            if (userList != null){
                controllerReturn.setData(userList);
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

    @ResponseBody
    @RequestMapping(value = "/selectAllPolicyDocument")
    public ControllerReturn selectAllPolicyDocument() {
        ControllerReturn controllerReturn = new ControllerReturn();
        try {
            List<PolicyDocument> PolicyDocumentList = policyDocumentService.selectAllPolicyDocument();
            if (PolicyDocumentList != null){
                controllerReturn.setData(PolicyDocumentList);
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

