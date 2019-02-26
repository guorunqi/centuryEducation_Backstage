package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.*;
import com.example.demo.service.*;
import com.example.demo.util.CommonUtil;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class QuestionnaireManagementController {
    @Autowired
    private QuestionnaireManagementService questionnaireManagementService;
    @Autowired
    private OrgService orgService;
    @Autowired
    private projectManagementService projectManagementService;
    @Autowired
    private ProblemService problemService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private ProjectOrgService projectOrgService;
    @Autowired
    private AnswerResultService answerResultService;

    @ResponseBody
    @RequestMapping(value = "/QuestionnaireManagementLoad")
    public ControllerReturn QuestionnaireManagementLoad(String name, String proName, String crowdOriented,String type){
        ControllerReturn controllerReturn = new ControllerReturn();
        CommonUtil commonutil = new CommonUtil();
        try {
            List<Questionnaire> questionnaires =questionnaireManagementService.selectQuestionnaireManagementList(name,proName,crowdOriented,type);
            List list = new ArrayList();
            if (questionnaires.size() > 0){
                for (Questionnaire questionnaire : questionnaires){
                    Map map = new HashMap();
                    Project project = projectManagementService.selectProjectByPrimaryKey(questionnaire.getProjectId());
                    map.putAll(commonutil.objectToMap(questionnaire));
                    map.put("proName",project.getName());
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

    @ResponseBody
    @RequestMapping(value = "/SaveQuestionnaire",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn SaveQuestionnaire(@RequestBody String data){
        ControllerReturn controllerReturn = new ControllerReturn();
        String jsonString= JSONObject.parseObject(data).get("data").toString();
        Map map = (Map) JSON.parse(jsonString);
        Questionnaire questionnaire=JSONObject.parseObject(jsonString,Questionnaire.class);
        questionnaire.setProjectId((String) map.get("proId"));
        questionnaire.setId(CommonUtil.getPrimaryKey());
        List QuestionnaireData = (List) map.get("QuestionnaireData");
        try {
            int rtnQuestionnaire = questionnaireManagementService.saveQuestionnaire(questionnaire);
            if(rtnQuestionnaire > 0 ){
                if(QuestionnaireData.size() > 0){
                    for(int i=0;i<QuestionnaireData.size();i++){
                        Map QuestionnaireDataMap = (Map)QuestionnaireData.get(i);
                        List answerData = (List) QuestionnaireDataMap.get("answerData");
                        Problem problem = new Problem();
                        problem.setId(CommonUtil.getPrimaryKey());
                        problem.setQuestionnaireId(questionnaire.getId());
                        problem.setContent((String) QuestionnaireDataMap.get("content"));
                        problem.setExhibitionType((String)QuestionnaireDataMap.get("exhibitionType"));
                        problem.setAnswerType((String)QuestionnaireDataMap.get("answerType"));
                        int rtnProblem = problemService.insertProblem(problem);
                        if(rtnProblem > 0){
                            if(answerData.size() > 0){
                                for (int j=0;j<answerData.size();j++){
                                    Map answerMap = (Map)answerData.get(i);
                                    Answer answer = new Answer();
                                    answer.setId(CommonUtil.getPrimaryKey());
                                    answer.setCode((String) answerMap.get("code"));
                                    answer.setContent((String) answerMap.get("content"));
                                    answer.setProblemId(problem.getId());
                                    answerService.insertAnswer(answer);
                                }

                            }
                        }
                    }
                }
                controllerReturn.setCode("true");
                controllerReturn.setMessage("保存成功");
            }
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.getMessage().toString());
        }
        return controllerReturn;
    }

    @ResponseBody
    @RequestMapping(value = "/EditQuestionnaireManagementLoad")
    public ControllerReturn EditQuestionnaireManagementLoad(String QuestionnaireId){
        ControllerReturn controllerReturn = new ControllerReturn();
        CommonUtil commonutil = new CommonUtil();
        try{
            Map questionnaireMap = null;
            if(QuestionnaireId != null && QuestionnaireId != ""){
                Questionnaire questionnaire = questionnaireManagementService.selectByPrimaryKey(QuestionnaireId);
                questionnaireMap  = CommonUtil.objectToMap(questionnaire);
                // QuestionnaireData
                List<Problem> ProblemList = problemService.selectByExample(questionnaire.getId());
                if (ProblemList.size() > 0){
                    for (int i=0;i<ProblemList.size();i++){
                        Problem problem = ProblemList.get(i);
                        Map problemMap  = CommonUtil.objectToMap(problem);
                        AnswerExample answerExample = new AnswerExample();
                        answerExample.or().andProblemIdEqualTo(problem.getId());
                        List<Answer> AnswerList = answerService.selectByExample(answerExample);
                        problemMap.put("answerData",AnswerList);
                        questionnaireMap.put("QuestionnaireData",problemMap);
                    }
                }
            }

            controllerReturn.setData(questionnaireMap);
            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/FillAnswerQuestionnaireManagementLoad")
    public ControllerReturn FillAnswerQuestionnaireManagementLoad(String QuestionnaireId){
        ControllerReturn controllerReturn = new ControllerReturn();
        CommonUtil commonutil = new CommonUtil();
        try{
            Map questionnaireMap = null;
            if(QuestionnaireId != null && QuestionnaireId != ""){
                Questionnaire questionnaire = questionnaireManagementService.selectByPrimaryKey(QuestionnaireId);
                questionnaireMap  = CommonUtil.objectToMap(questionnaire);
                List<Map> OrgData = new ArrayList();
                List<ProjectOrg> ProjectOrgData = projectOrgService.selectOrgByProjectId(questionnaire.getProjectId());
                for (ProjectOrg projectOrg : ProjectOrgData){
                    Map orgMap = CommonUtil.objectToMap(orgService.selectOrgByOrgId(projectOrg.getId()));
                    orgMap.put("projectOrgId",projectOrg.getId());
                    OrgData.add(orgMap);
                }
                questionnaireMap.put("OrgData",OrgData);
                List<Problem> ProblemList = problemService.selectByExample(questionnaire.getId());
                if (ProblemList.size() > 0){
                    for (int i=0;i<ProblemList.size();i++){
                        Problem problem = ProblemList.get(i);
                        Map problemMap  = CommonUtil.objectToMap(problem);
                        AnswerExample answerExample = new AnswerExample();
                        answerExample.or().andProblemIdEqualTo(problem.getId());
                        List<Answer> AnswerList = answerService.selectByExample(answerExample);
                        problemMap.put("answerData",AnswerList);
                        questionnaireMap.put("QuestionnaireData",problemMap);
                    }
                }
            }
            controllerReturn.setData(questionnaireMap);
            controllerReturn.setCode("true");
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/SaveEditQuestionnaire",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn SaveEditQuestionnaire(@RequestBody String data){
        ControllerReturn controllerReturn = new ControllerReturn();
        String jsonString= JSONObject.parseObject(data).get("data").toString();
        Map map = (Map) JSON.parse(jsonString);
        Questionnaire questionnaire=JSONObject.parseObject(jsonString,Questionnaire.class);
        List QuestionnaireData = (List) map.get("QuestionnaireData");
        try {
            int rtnQuestionnaire = questionnaireManagementService.updateByPrimaryKey(questionnaire);
            if(rtnQuestionnaire > 0 ){
                if(QuestionnaireData.size() > 0){
                    List<Problem> ProblemList = problemService.selectByExample(questionnaire.getId());
                    List<String> ProblemOldList = new ArrayList<>();
                    for (int p=0;p<ProblemList.size();p++){
                        Problem problem = (Problem)ProblemList.get(p);
                        ProblemOldList.add(problem.getId());
                    }
                    List<String> ProblemNewList = new ArrayList<>();

                    for(int i=0;i<QuestionnaireData.size();i++){
                        Map QuestionnaireDataMap = (Map)QuestionnaireData.get(i);
                        if ((String)QuestionnaireDataMap.get("id") == null || (String) QuestionnaireDataMap.get("id") == ""){
                            List answerData = (List) QuestionnaireDataMap.get("answerData");
                            Problem problem = new Problem();
                            problem.setId(CommonUtil.getPrimaryKey());
                            problem.setQuestionnaireId(questionnaire.getId());
                            problem.setContent((String) QuestionnaireDataMap.get("content"));
                            problem.setExhibitionType((String)QuestionnaireDataMap.get("exhibitionType"));
                            problem.setAnswerType((String)QuestionnaireDataMap.get("answerType"));
                            int rtnProblem = problemService.insertProblem(problem);
                            if(rtnProblem > 0){
                                if(answerData.size() > 0){
                                    for (int j=0;j<answerData.size();j++){
                                        Map answerMap = (Map)answerData.get(i);
                                        Answer answer = new Answer();
                                        answer.setId(CommonUtil.getPrimaryKey());
                                        answer.setCode((String) answerMap.get("code"));
                                        answer.setContent((String) answerMap.get("content"));
                                        answer.setProblemId(problem.getId());
                                        answerService.insertAnswer(answer);
                                    }
                                }
                            }
                        }else{
                            ProblemNewList.add((String)QuestionnaireDataMap.get("id"));
                            List answerData = (List) QuestionnaireDataMap.get("answerData");
                            Problem problem = new Problem();
                            problem.setId((String) QuestionnaireDataMap.get("id"));
                            problem.setQuestionnaireId((String) QuestionnaireDataMap.get("questionnaireId"));
                            problem.setContent((String) QuestionnaireDataMap.get("content"));
                            problem.setExhibitionType((String)QuestionnaireDataMap.get("exhibitionType"));
                            problem.setAnswerType((String)QuestionnaireDataMap.get("answerType"));
                            int rtnProblem = problemService.updateByPrimaryKey(problem);
                            if(answerData.size() > 0){
                                AnswerExample answerExample = new AnswerExample();
                                answerExample.or().andIdEqualTo(problem.getId());
                                List<Answer> AnswerList = answerService.selectByExample(answerExample);

                                List<String> AnswerNewList = new ArrayList<>();
                                List<String> AnswerOldList = new ArrayList<>();
                                for (int r=0;r<AnswerList.size();r++){
                                    AnswerOldList.add(AnswerList.get(r).getId());
                                }
                                for (int j=0;j<answerData.size();j++){
                                    Map answerMap = (Map)answerData.get(i);
                                    if((String) answerMap.get("id") == null || (String) answerMap.get("id") == ""){
                                        Answer answer = new Answer();
                                        answer.setId(CommonUtil.getPrimaryKey());
                                        answer.setCode((String) answerMap.get("code"));
                                        answer.setContent((String) answerMap.get("content"));
                                        answer.setProblemId(problem.getId());
                                        answerService.insertAnswer(answer);
                                    }else{
                                        AnswerNewList.add((String) answerMap.get("id"));
                                        Answer answer = new Answer();
                                        answer.setId((String) answerMap.get("id"));
                                        answer.setCode((String) answerMap.get("code"));
                                        answer.setContent((String) answerMap.get("content"));
                                        answer.setProblemId((String) answerMap.get("problemId"));
                                        answerService.updateByPrimaryKey(answer);
                                    }
                                }
                                for (int d=0;d<AnswerOldList.size();d++){
                                    boolean bon = AnswerNewList.contains(AnswerOldList.get(d));
                                    if(!bon){
                                        answerService.deleteByPrimaryKey(AnswerOldList.get(d));
                                    }
                                }
                            }
                        }
                        for (int d=0;d<ProblemOldList.size();d++){
                            boolean bon = ProblemNewList.contains(ProblemOldList.get(d));
                            if(!bon){
                                problemService.deleteByPrimaryKey(ProblemOldList.get(d));
                            }
                        }
                    }
                }
                controllerReturn.setCode("true");
                controllerReturn.setMessage("保存成功");
            }
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.getMessage().toString());
        }
        return controllerReturn;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteQuestionnaire")
    public ControllerReturn deleteQuestionnaire(String id){
        ControllerReturn controllerReturn = new ControllerReturn();
        try {
            if(id != null && id != ""){
                //查询问卷
                Questionnaire questionnaire = questionnaireManagementService.selectByPrimaryKey(id);
                if(questionnaire != null ){
                    // QuestionnaireData
                    List<Problem> ProblemList = problemService.selectByExample(questionnaire.getId());
                    if (ProblemList.size() > 0){
                        for (int i=0;i<ProblemList.size();i++){
                            Problem problem = ProblemList.get(i);
                            AnswerExample answerExample = new AnswerExample();
                            answerExample.or().andProblemIdEqualTo(problem.getId());
                            List<Answer> AnswerList = answerService.selectByExample(answerExample);
                            if(AnswerList.size() > 0 ){
                                for (int k=0;k<AnswerList.size();k++){
                                    Answer answer = AnswerList.get(k);
                                    answerService.deleteByPrimaryKey(answer.getId());
                                }
                            }
                            problemService.deleteByPrimaryKey((problem.getId()));
                        }
                    }
                    questionnaireManagementService.deleteByPrimaryKey(id);
                }
                controllerReturn.setCode("true");
                controllerReturn.setMessage("删除成功");
            }else{
                controllerReturn.setCode("false");
                controllerReturn.setMessage("删除失败");
            }

        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.getMessage().toString());
            System.out.println();
        }
        return controllerReturn;
    }

    @ResponseBody
    @RequestMapping(value = "/saveSelectionRate",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ControllerReturn saveSelectionRate(@RequestBody String data){
        ControllerReturn controllerReturn = new ControllerReturn();
        String jsonString= JSONObject.parseObject(data).get("data").toString();
        Map map = new HashMap();
        List SelectionRateList = (List) JSONArray.parseArray(jsonString, Object.class);
        try {
            for (int i=0;i<SelectionRateList.size();i++){
                Map SelectionRateMap = (Map) SelectionRateList.get(i);
                Map rateMap = (Map) SelectionRateMap.get("org");
                answerResult answerResult = new answerResult();
                answerResult.setId(CommonUtil.getPrimaryKey());
                answerResult.setProjectOrgId(rateMap.get("projectOrgId").toString());
                answerResult.setAnswerId(SelectionRateMap.get("id").toString());
                answerResult.setSelectionRate(Float.parseFloat(SelectionRateMap.get("selectionRate").toString()));
                answerResultService.insert(answerResult);
            }


                controllerReturn.setCode("true");
                controllerReturn.setMessage("保存成功");

        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.getMessage().toString());
        }
        return controllerReturn;
    }

}
