package com.example.demo.service;

import com.example.demo.dao.GradeMapper;
import com.example.demo.domain.Grade;
import com.example.demo.domain.GradeExample;
import com.example.demo.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by guorunqi on 2019/2/25.
 */
@Service
public class GradeService {
    @Resource
    private GradeMapper gradeMapper;
    public List<Grade> queryGradeByAssessmentID(String id){
        GradeExample gradeExample=new GradeExample();
        gradeExample.or().andAssessmentIdEqualTo(id);
        return gradeMapper.selectByExample(gradeExample);
    }
    public Boolean insetGrades(List<Grade> grades){
        try {
            for(Grade grade:grades){
                grade.setId(CommonUtil.getPrimaryKey());
                gradeMapper.insert(grade);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public Boolean deleteGradesByAssessmentID(String id){
        GradeExample gradeExample=new GradeExample();
        gradeExample.or().andAssessmentIdEqualTo(id);
        if(gradeMapper.deleteByExample(gradeExample)>-1){
            return true;
        }else{
            return false;
        }
    }
}
