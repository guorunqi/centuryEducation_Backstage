package com.example.demo.service;
import com.example.demo.dao.QuestionnaireMapper;
import com.example.demo.domain.Questionnaire;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionnaireManagementService {

    @Resource
    private QuestionnaireMapper questionnaireMapper;

    public List<Questionnaire> selectQuestionnaireManagementList(String name, String proName, String crowdOriented,String type){
        return questionnaireMapper.selectQuestionnaireManagementList(name,proName,crowdOriented,type);
    }

    public  int saveQuestionnaire(Questionnaire questionnaire){
        return questionnaireMapper.insert(questionnaire);
    }

    public  Questionnaire selectByPrimaryKey(String  id){
        return questionnaireMapper.selectByPrimaryKey(id);
    }

    public  int updateByPrimaryKey(Questionnaire  questionnaire){
        return questionnaireMapper.updateByPrimaryKey(questionnaire);
    }

    public  int deleteByPrimaryKey(String  id){
        return questionnaireMapper.deleteByPrimaryKey(id);
    }
}
