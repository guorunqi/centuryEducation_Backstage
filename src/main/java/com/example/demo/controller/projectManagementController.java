package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.domain.*;
import com.example.demo.service.*;
import com.example.demo.util.CommonUtil;
import com.example.demo.util.DateUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@Controller
public class projectManagementController {
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

    @RequestMapping(value = "/projectManagementLoad")
    @ResponseBody
    public ControllerReturn loginVeify(){
        ControllerReturn controllerReturn=new ControllerReturn();
        try {
            List<Project> projectList = loginService.selectAllProject();
            if(projectList != null){
                controllerReturn.setData(projectList);
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
     *保存项目
     * @param linkedMap
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/SaveProjectData",method = {RequestMethod.POST})
    public ControllerReturn SaveProjectData(@RequestBody Map linkedMap) {
        ControllerReturn controllerReturn = new ControllerReturn();
        try {
            DateUtil dataUtil = new DateUtil();
            String jsonStr= JSON.toJSONString(linkedMap);
            JSONObject JsonObjData = new JSONObject(jsonStr);
            JSONArray filds = (JSONArray)JsonObjData.get("filds");
            JSONArray SpecialistTable = (JSONArray)JsonObjData.get("SpecialistTable");
            JSONObject projectObj = (JSONObject)JsonObjData.get("project");

            //保存项目表
            String ProjectPrimaryKey = CommonUtil.getPrimaryKey();
            Project project = new Project();
            project.setId(ProjectPrimaryKey);
            project.setClassOne(projectObj.getString("classOne"));
            project.setClassTwo(projectObj.getString("classTwo"));
            project.setName(projectObj.getString("name"));
            project.setEndTime(dataUtil.getDateFormat(projectObj.getString("endTime")));
            project.setStartTime(dataUtil.getDateFormat(projectObj.getString("startTime")));
            int rtnProject = loginService.insertProject(project);

            //保存项目文件
            for (int i = 0; i < filds.length(); i++) {
                ProjectPolicyDocumentKey projectPolicyDocumentKey = new ProjectPolicyDocumentKey();
                JSONObject item = filds.getJSONObject(i);
                projectPolicyDocumentKey.setPolicyDocumentId(item.getString("id"));
                projectPolicyDocumentKey.setProjectId(ProjectPrimaryKey);
                int rtnProPol = projectPolicyDocumentService.insertProjectPolicyDocument(projectPolicyDocumentKey);
            }

            //涉及专家 SpecialistSchool
            for (int i = 0; i < SpecialistTable.length(); i++) {
                JSONObject specialistTable = SpecialistTable.getJSONObject(i);
                //获取机构信息
                Organization organization = orgServer.selectOrgBycode(specialistTable.getString("SpecialistSchool"));
                //插入项目机构表数据
                ProjectOrg projectOrg = new ProjectOrg();
                projectOrg.setId(CommonUtil.getPrimaryKey());
                projectOrg.setProId(ProjectPrimaryKey);
                projectOrg.setOrgId(organization.getId());
                projectOrgService.insertProjectOrg(projectOrg);

                ProjectOrgUser ProjectOrgUser = new ProjectOrgUser();
                ProjectOrgUser.setId(CommonUtil.getPrimaryKey());
                ProjectOrgUser.setProId(ProjectPrimaryKey);
                ProjectOrgUser.setOrgId(organization.getId());
                ProjectOrgUser.setUserId(specialistTable.getString("SpecialistId"));
                //插入项目机构专家表表数据
                projectOrgUserService.insertProjectOrgUser(ProjectOrgUser);
                //插入项目机构表数据
            }
            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }

    /**
     * 根据项目ID删除项目
     * @param projectId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteProject")
    public ControllerReturn deleteProject(String projectId){
        ControllerReturn controllerReturn = new ControllerReturn();
        try {
            //根据项目ID ，删除项目机构专家表
            ProjectOrgUserExample projectOrgUserExample = new ProjectOrgUserExample();
            projectOrgUserExample.or().andProIdEqualTo(projectId);
            projectOrgUserService.deleteProjectOrgUser(projectOrgUserExample);
            //根据项目ID，删除项目文件表
            projectPolicyDocumentService.deleteProjectPolicyDocument(projectId);
            //根据项目ID删除项目
            loginService.deleteProjectByProjectId(projectId);

            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }

    }

    /**
     * 编辑查询
     * @param projectId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectProjectEdit")
    public ControllerReturn selectProjectEdit(String projectId){
        ControllerReturn controllerReturn = new ControllerReturn();
        try {
            //根据项目ID 查询ProjectOrg
            Project project = loginService.selectProjectByProjectId(projectId);
            String[] Orgs;
            List<PolicyDocument> PolicyDocumentList = new ArrayList<PolicyDocument>();
            List<Map> SpecialistList = new ArrayList<Map>();

            //查询org并转成数组
            List<ProjectOrgUser> projectOrgUser = projectOrgUserService.selectOrgUserByProjectId(projectId);
            // List<ProjectOrg> ProjectOrgs = projectOrgService.selectOrgByProjectId(projectId);
            Orgs =new String[projectOrgUser.size()];
            for(int i=0 ; i<projectOrgUser.size() ; i++){
                Map map = new HashMap();
                Organization Org = orgServer.selectOrgByOrgId(projectOrgUser.get(i).getOrgId());
                Orgs[i] = Org.getCode();
                User user = userService.selectSpecialistById(projectOrgUser.get(i).getUserId());
                map.put("ProjectOrgUserId",projectOrgUser.get(i).getId());
                map.put("OrgId",Org.getId());
                map.put("OrgName",Org.getName());
                map.put("OrgCode",Org.getCode());
                map.put("userId",user.getId());
                map.put("userName",user.getName());
                map.put("userPhone",user.getPhone());
                map.put("userRemarks",user.getRemarks());
                SpecialistList.add(map);
            }
            //根据项目ID 查询相关文件
            List<ProjectPolicyDocumentKey> projectPolicyDocuments = projectPolicyDocumentService.selectPolicyDocumentByProjectId(projectId);
            for(ProjectPolicyDocumentKey projectPolicyDocument : projectPolicyDocuments){
                PolicyDocument policyDocument = policyDocumentService.selectPolicyDocumentById(projectPolicyDocument.getPolicyDocumentId());
                PolicyDocumentList.add(policyDocument);
            }

            Map<String,Object> map = new HashMap();
            map.put( "project" , project );
            map.put( "Orgs" , Orgs );
            map.put( "PolicyDocumentList" , PolicyDocumentList );
            map.put( "SpecialistList" , SpecialistList );

            controllerReturn.setData(map);
            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }

    }

    /**
     * 新增相关文件
     * @param projectId
     * @param policyDocumentId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertProjectPolicyDocument")
    public ControllerReturn insertProjectPolicyDocument(String projectId,String policyDocumentId){
        ControllerReturn controllerReturn = new ControllerReturn();
        try {
            //根据项目ID，政策文件ID 插入一条数据
            ProjectPolicyDocumentKey projectPolicyDocumentKey = new ProjectPolicyDocumentKey();
            projectPolicyDocumentKey.setProjectId(projectId);
            projectPolicyDocumentKey.setPolicyDocumentId(policyDocumentId);
            projectPolicyDocumentService.insertProjectPolicyDocument(projectPolicyDocumentKey);
            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }

    }

    /**
     * 删除相关文件
     * @param projectId
     * @param policyDocumentId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteProjectPolicyDocument")
    public ControllerReturn deleteProjectPolicyDocument(String projectId,String policyDocumentId){
        ControllerReturn controllerReturn = new ControllerReturn();
        try {
            //根据项目ID，政策文件ID 删除一条数据
            ProjectPolicyDocumentExample projectPolicyDocumentExample = new ProjectPolicyDocumentExample();
            projectPolicyDocumentExample.or().andProjectIdEqualTo(projectId);
            projectPolicyDocumentExample.or().andPolicyDocumentIdEqualTo(policyDocumentId);
            projectPolicyDocumentService.deleteProjectPolicyDocumentByExample(projectPolicyDocumentExample);
            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }

    /**
     * 删除专家文件
     * @param ProjectOrgUserId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteProjectOrgUser")
    public ControllerReturn deleteProjectOrgUser(String ProjectOrgUserId){
        ControllerReturn controllerReturn = new ControllerReturn();
        try {
            //根据主键，删除一条数据
            projectOrgUserService.deleteProjectOrgUserByPrimetonKey(ProjectOrgUserId);
            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }

    /**
     * 新增专家文件
     * @param projectId
     * @param orgCode
     * *@param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertProjectOrgUser")
    public ControllerReturn insertProjectOrgUser(String projectId,String orgCode,String userId){
        ControllerReturn controllerReturn = new ControllerReturn();
        try {
            //根据OrgCode查询org信息
            Organization organization = orgServer.selectOrgBycode(orgCode);
            //根据项目ID，政策文件ID 新增一条数据
            ProjectOrgUser projectOrgUser = new ProjectOrgUser();
            projectOrgUser.setId(CommonUtil.getPrimaryKey());
            projectOrgUser.setUserId(userId);
            projectOrgUser.setOrgId(organization.getId());
            projectOrgUser.setProId(projectId);
            projectOrgUserService.insertProjectOrgUser(projectOrgUser);

            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }

    /**
     *更新项目
     * @param linkedMap
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upDataProjectData",method = {RequestMethod.POST})
    public ControllerReturn upDataProjectData(@RequestBody Map linkedMap) {
        ControllerReturn controllerReturn = new ControllerReturn();
        try {
            DateUtil dataUtil = new DateUtil();
            String jsonStr= JSON.toJSONString(linkedMap);
            JSONObject JsonObjData = new JSONObject(jsonStr);
            JSONObject projectObj = (JSONObject)JsonObjData.get("project");

            //保存项目表
            Project project = projectManagementService.selectProjectByPrimaryKey(projectObj.getString("id"));
            project.setClassOne(projectObj.getString("classOne"));
            project.setClassTwo(projectObj.getString("classTwo"));
            project.setName(projectObj.getString("name"));
            project.setEndTime(dataUtil.getDateFormat(projectObj.getString("endTime")));
            project.setStartTime(dataUtil.getDateFormat(projectObj.getString("startTime")));
            loginService.upDataProject(project);

            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/projectLoad")
    public ControllerReturn projectLoad(String name,String classOne,String status,String orgs,String classTwo){
        ControllerReturn controllerReturn = new ControllerReturn();
        try {

            List<Project> projectList;
            //根据项目ID，政策文件ID 新增一条数据
            if (orgs != ""){
                Organization organization = orgServer.selectOrgBycode(orgs);
                projectList = loginService.selectByCondition(name,classOne,status,classTwo,organization.getId());
            }else{
                projectList = loginService.selectByCondition(name,classOne,status,classTwo,orgs);
            }

            controllerReturn.setData(projectList);
            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }
}
