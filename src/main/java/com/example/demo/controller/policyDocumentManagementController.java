package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.domain.ControllerReturn;
import com.example.demo.domain.PolicyDocument;
import com.example.demo.domain.PolicyDocumentEntry;
import com.example.demo.domain.PolicyDocumentEntryExample;
import com.example.demo.service.PolicyDocumentEntryService;
import com.example.demo.service.PolicyDocumentService;
import com.example.demo.service.projectManagementService;
import com.example.demo.util.CommonUtil;
import com.example.demo.util.TreeTablePojo;
import com.example.demo.util.TreeTableUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class policyDocumentManagementController {
    @Autowired
    private projectManagementService loginService;
    @Autowired
    private PolicyDocumentService policyDocumentService;
    @Autowired
    private PolicyDocumentEntryService policyDocumentEntryService;
    /**
     * 根据查询条件查询政策文件
     * @param policyName
     * @param classOne
     * @param classTwo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/policyDocumentManagementLoad")
    public ControllerReturn projectLoad(String policyName,String classOne,String classTwo){
        ControllerReturn controllerReturn = new ControllerReturn();
        try {
            List<PolicyDocument> policyDocumentList =policyDocumentService.selectAllPolicyDocumentByCondition(policyName,classOne,classTwo);
            controllerReturn.setData(policyDocumentList);
            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }

    /**
     *保存政策文件
     * @param linkedMap
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/SavePolicyDocumentManagement",method = {RequestMethod.POST})
    public ControllerReturn SavePolicyDocumentManagement(@RequestBody Map linkedMap) {
        ControllerReturn controllerReturn = new ControllerReturn();
        try {
            String jsonStr= JSON.toJSONString(linkedMap);
            JSONObject JsonObjData = new JSONObject(jsonStr);
            JSONObject policyDocumentData = (JSONObject)JsonObjData.get("policyDocument");
            String policyDocumentId = "";
            if (policyDocumentData.has("id")){
                policyDocumentId = policyDocumentData.getString("id");
            }
            //保存政策文件表
            PolicyDocument policyDocument = new PolicyDocument();

            policyDocument.setPolicyName(policyDocumentData.getString("policyName"));
            policyDocument.setClassOne(policyDocumentData.getString("classOne"));
            policyDocument.setClassTwo(policyDocumentData.getString("classTwo"));
            policyDocument.setRemarks(policyDocumentData.getString("remarks"));
            policyDocument.setCreateTime(new Date());
            if (policyDocumentId == "" || policyDocumentId == null){
                policyDocument.setId(CommonUtil.getPrimaryKey());
                policyDocumentService.insertPolicyDocument(policyDocument);
            }else {
                policyDocument.setId(policyDocumentId);
                policyDocumentService.upDataPolicyDocument(policyDocument);
            }


            controllerReturn.setData(policyDocument);
            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/selectPolicyDocumentEntryByPolicyDocumentId")
    public ControllerReturn selectPolicyDocumentEntryByPolicyDocumentId(String PolicyDocumentId) {
        ControllerReturn controllerReturn = new ControllerReturn();
        TreeTableUtil treeTableUtil = new TreeTableUtil();
        try {
            PolicyDocument policyDocument = policyDocumentService.selectPolicyDocumentById(PolicyDocumentId);
            PolicyDocumentEntryExample policyDocumentEntryExample = new PolicyDocumentEntryExample();
            policyDocumentEntryExample.or().andPolicyDocumentIdEqualTo(PolicyDocumentId);
            List<PolicyDocumentEntry> PolicyDocumentEntryList = policyDocumentEntryService.selectPolicyDocumentEntryByExample(policyDocumentEntryExample);
            List<TreeTablePojo> treeTable = treeTableUtil.PolicyDocumentEntryToTree(PolicyDocumentEntryList);
            List<TreeTablePojo> treeTableData =treeTableUtil.getTree(treeTable);
            Map map = new HashMap();
            map.put("policyDocument",policyDocument);
            map.put("treeTableData",treeTableData);
            controllerReturn.setData(map);
            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/AddPolicyDocumentEntry")
    public ControllerReturn AddPolicyDocumentEntry(String PolicyDocumentId,String content,String name) {
        ControllerReturn controllerReturn = new ControllerReturn();
        try {
            PolicyDocumentEntry policyDocumentEntry = new PolicyDocumentEntry();
            policyDocumentEntry.setId(CommonUtil.getPrimaryKey());
            policyDocumentEntry.setContent(content);
            policyDocumentEntry.setName(name);
            policyDocumentEntry.setPolicyDocumentId(PolicyDocumentId);
            policyDocumentEntryService.insertPolicyDocumentEntry(policyDocumentEntry);
            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }
    @ResponseBody
    @RequestMapping(value = "/AddSubordinatePolicyDocumentEntry")
    public ControllerReturn AddSubordinatePolicyDocumentEntry(String PolicyDocumentId,String content,String name,String pid) {
        ControllerReturn controllerReturn = new ControllerReturn();
        try {
            PolicyDocumentEntry policyDocumentEntry = new PolicyDocumentEntry();
            policyDocumentEntry.setId(CommonUtil.getPrimaryKey());
            policyDocumentEntry.setContent(content);
            policyDocumentEntry.setName(name);
            policyDocumentEntry.setParentId(pid);
            policyDocumentEntry.setPolicyDocumentId(PolicyDocumentId);
            policyDocumentEntryService.insertPolicyDocumentEntry(policyDocumentEntry);
            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/EditSubordinatePolicyDocumentEntry")
    public ControllerReturn EditSubordinatePolicyDocumentEntry(String PolicyDocumentId,String content,String name,String pid,String id) {
        ControllerReturn controllerReturn = new ControllerReturn();
        try {
            PolicyDocumentEntry policyDocumentEntry = new PolicyDocumentEntry();
            policyDocumentEntry.setId(id);
            policyDocumentEntry.setContent(content);
            policyDocumentEntry.setName(name);
            policyDocumentEntry.setParentId(pid);
            policyDocumentEntry.setPolicyDocumentId(PolicyDocumentId);
            int l = policyDocumentEntryService.upDataPolicyDocumentEntry(policyDocumentEntry);
            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/selectEditSubordinatePolicyDocumentEntry")
    public ControllerReturn selectEditSubordinatePolicyDocumentEntry(String id) {
        ControllerReturn controllerReturn = new ControllerReturn();
        try {
            controllerReturn.setData(policyDocumentEntryService.selectPolicyDocumentEntryByPrimaryKey(id));
            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/DeleteSubordinatePolicyDocumentEntry")
    public ControllerReturn DeleteSubordinatePolicyDocumentEntry(String id) {
        ControllerReturn controllerReturn = new ControllerReturn();
        try {
            policyDocumentEntryService.deleteSubordinatePolicyDocumentEntryById(id);
            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/DeleteSubordinatePolicyDocument")
    public ControllerReturn DeleteSubordinatePolicyDocument(String id) {
        ControllerReturn controllerReturn = new ControllerReturn();
        try {
            policyDocumentEntryService.deletePolicyDocumentEntryByPolicyDocumentId(id);
            policyDocumentService.deletePolicyDocument(id);
            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }

}
