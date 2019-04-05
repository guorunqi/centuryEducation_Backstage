package com.example.demo.service;

import com.example.demo.dao.AssessmentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by guorunqi on 2019/3/31.
 */
@Service
public class AssessmentResultService {
    @Resource
    private AssessmentMapper assessmentMapper;
}
