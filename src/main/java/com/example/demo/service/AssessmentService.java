package com.example.demo.service;

import com.example.demo.dao.AssessmentMapper;
import com.example.demo.dao.GradeMapper;
import com.example.demo.domain.*;
import com.example.demo.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by guorunqi on 2019/2/24.
 */
@Service
public class AssessmentService {
    @Resource
    private AssessmentMapper assessmentMapper;
    @Resource
    private GradeMapper gradeMapper;
    public Assessment queryAssessmentById(String id){
        return assessmentMapper.selectByPrimaryKey(id);
    }
//    public Boolean deleteAssessments(List<Assessment> list){
//        try {
//            if(list.size()>0) {
//                for (Assessment assessment : list) {
//                    if (assessment.getId() != null && !assessment.getId().equals("")) {
//                        assessmentMapper.deleteByPrimaryKey(assessment.getId());
//                    }else{
//                        return false;
//                    }
//                }
//                return true;
//            }else{
//                return true;
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }
    public String saveAssessment(Assessment assessment, List<Grade> list){
        try {
            String id;
            if(StringUtils.isBlank(assessment.getId())){
                id=CommonUtil.getPrimaryKey();
                assessment.setId(id);
                assessmentMapper.insert(assessment);
            }else {
                id=assessment.getId();
                assessmentMapper.updateByPrimaryKeySelective(assessment);
            }
            for(Grade grade:list){
                grade.setId(CommonUtil.getPrimaryKey());
                grade.setAssessmentId(id);
                gradeMapper.insert(grade);
            }
            return assessment.getId();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    public List<HashMap> queryAssessment(String projectName,String name,Integer scoringType){
        return assessmentMapper.selectAssessment(projectName,name,scoringType);
    }
    public Boolean deleteAssessments(List<Assessment> assessments){
        try {
            if(assessments!=null&&assessments.size()>0){
                for(Assessment assessment:assessments){
                    if(!StringUtils.isBlank(assessment.getId())){
                        GradeExample gradeExample=new GradeExample();
                        gradeExample.or().andAssessmentIdEqualTo(assessment.getId());
                        gradeMapper.deleteByExample(gradeExample);
                        assessmentMapper.deleteByPrimaryKey(assessment.getId());
                    }
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public Assessment queryAssessmentByID(String id){
        return assessmentMapper.selectByPrimaryKey(id);
    }
}
