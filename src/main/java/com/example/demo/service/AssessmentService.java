package com.example.demo.service;

import com.example.demo.dao.AssessmentMapper;
import com.example.demo.domain.Assessment;
import com.example.demo.domain.AssessmentExample;
import com.example.demo.domain.SelfEvaluation;
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

}
