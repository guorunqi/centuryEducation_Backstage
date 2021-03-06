package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.ControllerReturn;
import com.example.demo.domain.ProjectOrgUser;
import com.example.demo.domain.Quota;
import com.example.demo.service.ProjectOrgUserService;
import com.example.demo.service.QuotaService;
import com.example.demo.util.TreeTablePojo;
import com.example.demo.util.TreeTableUtil;
import com.example.demo.util.TreeUtil;
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
 * Created by guorunqi on 2019/2/28.
 */
@Controller
public class QuotaController {
    @Autowired
    private QuotaService quotaService;
    @Autowired
    private ProjectOrgUserService projectOrgUserService;
    @ResponseBody
    @RequestMapping(value = "/queryQuotaByID",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn queryQuotaByID(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String id= JSONObject.parseObject(data).get("id").toString();
            if(!StringUtils.isBlank(id)){
                Quota quota=quotaService.queryQuotaById(id);
                controllerReturn.setCode("true");
                controllerReturn.setData(quota);
            }else{
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
    @ResponseBody
    @RequestMapping(value = "/saveQuota",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn saveQuota(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String jsonString=JSONObject.parseObject(data).get("data").toString();
            Quota quota=JSONObject.parseObject(jsonString,Quota.class);
            Float number=new Float(0);
            List<Quota> list=null;
            if(StringUtils.isBlank(quota.getpId())){
                list=quotaService.queryTopQuotaByAssessmentId(quota.getAssessmentId());
            }else{
                list=quotaService.queryNoTopQuotaByAssessmentId(quota.getAssessmentId(),quota.getpId(),quota.getId());
            }
            if(list!=null) {
                for (Quota q : list) {
                    if (q.getWeight() == null || q.getWeight().equals("")) {
                        number += 0;
                    } else if(q.getId().equals(quota.getId())){
                        number += 0;
                    }else {
                        number += q.getWeight();
                    }
                }
                number+=quota.getWeight();
                if(number>1.0){
                    controllerReturn.setCode("false");
                    controllerReturn.setMessage("权重相加总和大于1");
                    return controllerReturn;
                }
            }

            Quota quota1=quotaService.saveQuota(quota);
            if(quota1!=null){
                controllerReturn.setCode("true");
                controllerReturn.setData(quota1);
            }else{
                controllerReturn.setCode("false");
                controllerReturn.setData(null);
            }

        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            e.printStackTrace();
        }
        return controllerReturn;
    }
    @ResponseBody
    @RequestMapping(value="/deleteQuotas",method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ControllerReturn deleteQuotas(@RequestBody String data){
        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String assessmentId= JSONObject.parseObject(data).get("assessmentId").toString();
            String id= JSONObject.parseObject(data).get("id").toString();
            List<Quota> list=quotaService.queryQuotaByAssessmentId(assessmentId);
            TreeTableUtil treeTableUtil=new TreeTableUtil();
            List<TreeTablePojo> treeTablePojos=treeTableUtil.QuotaToTree(list);
            treeTablePojos=treeTableUtil.getTree(treeTablePojos);

            TreeTablePojo treeTablePojo=treeTableUtil.getTreeTablePojo(treeTablePojos,id);
            Boolean sign=quotaService.deleteQuota(treeTablePojo);
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
    @RequestMapping(value = "/queryQuotaByAssessmentId",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn queryQuotaByAssessmentId(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String assessmentId= JSONObject.parseObject(data).get("assessmentId").toString();
            if(!StringUtils.isBlank(assessmentId)){
                List<Quota> list=quotaService.queryQuotaByAssessmentId(assessmentId);
                TreeTableUtil treeTableUtil=new TreeTableUtil();
                List<TreeTablePojo> treeTablePojos=treeTableUtil.QuotaToTree(list);
                treeTablePojos=treeTableUtil.getTree(treeTablePojos);
                treeTablePojos=treeTableUtil.sortTreeTablePojos(treeTablePojos);
                controllerReturn.setCode("true");
                controllerReturn.setData(treeTablePojos);
            }else{
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
    @ResponseBody
    @RequestMapping(value = "/queryProjectOrgUserByProjectID",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn queryProjectOrgUserByProjectID(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String projectID= JSONObject.parseObject(data).get("projectID").toString();
            if(!StringUtils.isBlank(projectID)){
                List<HashMap> list=projectOrgUserService.queryProjectOrgUserByProjectID(projectID);
                controllerReturn.setCode("true");
                controllerReturn.setData(list);
            }else{
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
    @ResponseBody
    @RequestMapping(value = "/saveProjectOrgUsers",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn saveProjectOrgUsers(@RequestBody String data){

        ControllerReturn controllerReturn=new ControllerReturn();
        try{
            String jsonString= JSONObject.parseObject(data).get("data").toString();
            List<ProjectOrgUser> list= JSONArray.parseArray(jsonString,ProjectOrgUser.class);
            if(list.size()>0){
                Boolean sign=projectOrgUserService.saveProjectOrgUsers(list);
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
