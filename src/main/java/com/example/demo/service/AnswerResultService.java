package com.example.demo.service;

import com.example.demo.dao.answerResultMapper;
import com.example.demo.domain.answerResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AnswerResultService {

    @Resource
    private answerResultMapper answerResultMapper;

    public int insert (answerResult answerResult){
        return answerResultMapper.insert(answerResult);
    }
}
