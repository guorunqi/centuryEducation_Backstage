package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.*;
import com.example.demo.service.*;
import com.example.demo.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;


@Controller
public class projectController {
    @Autowired
    private projectManagementService loginService;
    @Autowired
    private ProjectPolicyDocumentService projectPolicyDocumentService;
    @Autowired
    private ProjectOrgService projectOrgService;
    @Autowired
    private ProjectOrgUserService projectOrgUserService;
    @Autowired
    private OrgService orgServer;
    @Autowired
    private UserService userService;
    @Autowired
    private PolicyDocumentService policyDocumentService;
    @Autowired
    private projectManagementService projectManagementService;

    /**
     * 保存项目文件
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/SaveProjectFile",method = {RequestMethod.POST})
    public ControllerReturn SaveProjectFile(@RequestBody Map linkedMap) {
        ControllerReturn controllerReturn = new ControllerReturn();
        try {
            String jsonStr = JSON.toJSONString(linkedMap);
            JSONObject JsonObjData = JSON.parseObject(jsonStr);
            JSONObject projectObj = (JSONObject) JsonObjData.get("AllPolicyDocument");
            String proId = (String) JsonObjData.get("proId");

            ProjectPolicyDocumentKey projectPolicyDocumentKey = new ProjectPolicyDocumentKey();
            projectPolicyDocumentKey.setPolicyDocumentId(projectObj.getString("id"));
            projectPolicyDocumentKey.setProjectId(proId);
            int rtnProPol = projectPolicyDocumentService.insertProjectPolicyDocument(projectPolicyDocumentKey);
            controllerReturn.setCode("true");
            return controllerReturn;
        } catch (Exception e) {
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }
    @ResponseBody
    @RequestMapping(value = "/DeleteProjectFile",method = {RequestMethod.POST})
    public ControllerReturn DeleteProjectFile(@RequestBody Map linkedMap){
        ControllerReturn controllerReturn = new ControllerReturn();
        try {
            String jsonStr= JSON.toJSONString(linkedMap);
            JSONObject JsonObjData = JSON.parseObject(jsonStr);
            String proId = (String)JsonObjData.get("proId");
            String PolicyDocumentId = (String)JsonObjData.get("AllPolicyDocumentId");
            ProjectPolicyDocumentExample projectPolicyDocumentExample = new ProjectPolicyDocumentExample();
            projectPolicyDocumentExample.or().andPolicyDocumentIdEqualTo(PolicyDocumentId);
            projectPolicyDocumentExample.or().andProjectIdEqualTo(proId);
            int rtnProPol = projectPolicyDocumentService.deleteProjectPolicyDocumentByExample(projectPolicyDocumentExample);
            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }
    @ResponseBody
    @RequestMapping(value = "/SaveProjectOrgUser",method = {RequestMethod.POST})
    public ControllerReturn SaveProjectOrgUser(@RequestBody Map linkedMap){
        ControllerReturn controllerReturn = new ControllerReturn();
        try {
            String jsonStr= JSON.toJSONString(linkedMap);
            JSONObject JsonObjData = JSON.parseObject(jsonStr);
            String proId = (String)JsonObjData.get("proId");
            String orgCode = (String)JsonObjData.get("orgCode");
            String AllSpeciaId = (String)JsonObjData.get("AllSpeciaId");

            Organization organization = orgServer.selectOrgBycode(orgCode);
            ProjectOrgUser projectOrgUser = new ProjectOrgUser();
            projectOrgUser.setId(CommonUtil.getPrimaryKey());
            projectOrgUser.setProId(proId);
            projectOrgUser.setUserId(AllSpeciaId);
            projectOrgUser.setOrgId(organization.getId());
            int s = projectOrgUserService.insertProjectOrgUser(projectOrgUser);
            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }
}
