package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.domain.*;
import com.example.demo.service.*;
import com.example.demo.util.CommonUtil;
import com.example.demo.util.DateUtil;
import net.minidev.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
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
            List list = new ArrayList();
            List<Project> projectList = loginService.selectAllProject();
            if(projectList.size()>0) {
                for (Project project : projectList) {
                    String proId = project.getId();
                    Map map = CommonUtil.objectToMap(project);;
                    List Orgs  = new ArrayList();
                    List<ProjectOrg> ProjectOrgList = projectOrgService.selectOrgByProjectId(proId);
                    if(ProjectOrgList != null ){
                        for (ProjectOrg ProjectOrg:ProjectOrgList){
                            String orhId = ProjectOrg.getOrgId();
                            Organization Org = orgServer.selectOrgByOrgId(orhId);
                            if (!Orgs.contains(Org.getName())){
                                Orgs.add(Org.getName());
                            }
                        }
                        String org = StringUtils.join(Orgs, ",");
                        map.put("org",org);
                    }
                    list.add(map);
                }
            }
            if(list != null){
                controllerReturn.setData(list);
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
            JSONObject projectObj = (JSONObject)JsonObjData.get("project");
            String orgs = (String)JsonObjData.get("orgs");

            //保存项目表
            String ProjectPrimaryKey = CommonUtil.getPrimaryKey();
            Project project = new Project();
            project.setId(ProjectPrimaryKey);
            project.setStutas("0");
            project.setClassOne(projectObj.getString("classOne"));
            project.setClassTwo(projectObj.getString("classTwo"));
            project.setName(projectObj.getString("name"));
            project.setEndTime(dataUtil.getDateFormat(projectObj.getString("endTime")));
            project.setStartTime(dataUtil.getDateFormat(projectObj.getString("startTime")));
            int rtnProject = loginService.insertProject(project);

            //String orgs = projectObj.getString("orgs");
            //选择的机构
            String[] orgArr = orgs.split(",");
            if(orgArr.length>0){
                for (int i = 0; i < orgArr.length; i++) {
                    //获取机构信息
                    Organization organization = orgServer.selectOrgBycode(orgArr[i]);
                    //插入项目机构表数据
                    ProjectOrg projectOrg = new ProjectOrg();
                    projectOrg.setId(CommonUtil.getPrimaryKey());
                    projectOrg.setProId(project.getId());
                    projectOrg.setOrgId(organization.getId());
                    projectOrgService.insertProjectOrg(projectOrg);
                }
            }
            controllerReturn.setData(project.getId());
            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }

/*
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
            project.setStutas("wfb");
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
*/

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
            int i = loginService.deleteProjectByProjectId(projectId);
            if(i == 0){
                controllerReturn.setCode("false");
                controllerReturn.setMessage("删除失败，该项目已被使用");
                return controllerReturn;
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
            Map<String,Object> mapData = new HashMap();
            List<String> orgList = new ArrayList<String>();
            mapData.put( "project" , project );
            List<ProjectOrg> projectOrg = projectOrgService.selectOrgByProjectId(projectId);
            if(projectOrg.size()>0){
                for(int i=0 ; i<projectOrg.size() ; i++){
                    Map map = new HashMap();
                    Organization Org = orgServer.selectOrgByOrgId(projectOrg.get(i).getOrgId());
                    orgList.add(Org.getCode());
                }
                mapData.put( "orgs" , orgList );
            }
            //查询org并转成数组
            List<ProjectOrgUser> projectOrgUser = projectOrgUserService.selectOrgUserByProjectId(projectId);
            Orgs =new String[projectOrgUser.size()];
            if(projectOrgUser.size()>0){
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
                mapData.put( "Orgs" , Orgs );
                mapData.put( "SpecialistList" , SpecialistList );
            }
            //根据项目ID 查询相关文件
            List<ProjectPolicyDocumentKey> projectPolicyDocuments = projectPolicyDocumentService.selectPolicyDocumentByProjectId(projectId);
            if(projectPolicyDocuments.size()>0){
                for(ProjectPolicyDocumentKey projectPolicyDocument : projectPolicyDocuments){
                    PolicyDocument policyDocument = policyDocumentService.selectPolicyDocumentById(projectPolicyDocument.getPolicyDocumentId());
                    PolicyDocumentList.add(policyDocument);
                }
                mapData.put( "PolicyDocumentList" , PolicyDocumentList );
            }
            controllerReturn.setData(mapData);
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
            int i = projectPolicyDocumentService.insertProjectPolicyDocument(projectPolicyDocumentKey);
            if(i == 0){
                controllerReturn.setCode("false");
                controllerReturn.setMessage("数据重复");
                return controllerReturn;
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
            int i =projectOrgUserService.insertProjectOrgUser(projectOrgUser);
            if(i == 0){
                controllerReturn.setCode("false");
                controllerReturn.setMessage("数据保存失败");
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
            String orgs = JsonObjData.getString("orgs");
            //保存项目表
            Project project = projectManagementService.selectProjectByPrimaryKey(projectObj.getString("id"));
            project.setClassOne(projectObj.getString("classOne"));
            project.setClassTwo(projectObj.getString("classTwo"));
            project.setName(projectObj.getString("name"));
            project.setEndTime(dataUtil.getDateFormat(projectObj.getString("endTime")));
            project.setStartTime(dataUtil.getDateFormat(projectObj.getString("startTime")));
            loginService.upDataProject(project);

            List<ProjectOrg> projectOrg = projectOrgService.selectOrgByProjectId(project.getId());
            if(projectOrg.size()>0){
                for(ProjectOrg Org:projectOrg){
                    projectOrgService.deleteOrgById(Org.getId());
                }
            }

            //选择的机构
            String[] orgArr = orgs.split(",");
            if(orgArr.length>0){
                for (int i = 0; i < orgArr.length; i++) {
                    //获取机构信息
                    Organization organization = orgServer.selectOrgBycode(orgArr[i]);
                    //插入项目机构表数据
                    ProjectOrg pOrg = new ProjectOrg();
                    pOrg.setId(CommonUtil.getPrimaryKey());
                    pOrg.setProId(project.getId());
                    pOrg.setOrgId(organization.getId());
                    projectOrgService.insertProjectOrg(pOrg);
                }
            }

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
            List list = new ArrayList();
            if(projectList.size()>0) {
                for (Project project : projectList) {
                    String proId = project.getId();
                    Map map = CommonUtil.objectToMap(project);;
                    List Orgs  = new ArrayList();
                    List<ProjectOrgUser> ProjectOrgUserList = projectOrgUserService.selectOrgUserByProjectId(proId);
                    if(ProjectOrgUserList.size()>0){
                        for (ProjectOrgUser ProjectOrgUser:ProjectOrgUserList){
                            String orhId = ProjectOrgUser.getOrgId();
                            Organization Org = orgServer.selectOrgByOrgId(orhId);
                            if (!Orgs.contains(Org.getName())){
                                Orgs.add(Org.getName());
                            }
                        }
                        String org = StringUtils.join(Orgs, ",");
                        map.put("org",org);
                    }
                    list.add(map);
                }
            }

            controllerReturn.setData(list);
            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }
}
