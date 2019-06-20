package com.example.demo.service;

import com.example.demo.dao.AssessmentMapper;
import com.example.demo.dao.AssessmentResultMapper;
import com.example.demo.domain.AssessmentExample;
import com.example.demo.domain.AssessmentResult;
import com.example.demo.domain.AssessmentResultExample;
import com.example.demo.util.CommonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by guorunqi on 2019/3/31.
 */
@Service
public class AssessmentResultService {
    @Resource
    private AssessmentResultMapper assessmentResultMapper;
    public String saveAssessmentResult(AssessmentResult assessmentResult){
        try {
            if(StringUtils.isBlank(assessmentResult.getId())){
                assessmentResult.setId(CommonUtil.getPrimaryKey());
                assessmentResultMapper.insert(assessmentResult);
            }else {
                assessmentResultMapper.updateByPrimaryKeySelective(assessmentResult);
            }
            return assessmentResult.getId();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    public List<AssessmentResult> queryAssessmentResultByAssessmentIdAndProjectOrgId(String assessmentId,String projectOrgId){
        AssessmentResultExample assessmentResultExample=new AssessmentResultExample();
        assessmentResultExample.or().andAssessmentIdEqualTo(assessmentId).andProjectOrgIdEqualTo(projectOrgId);
        return assessmentResultMapper.selectByExample(assessmentResultExample);

    }
}
