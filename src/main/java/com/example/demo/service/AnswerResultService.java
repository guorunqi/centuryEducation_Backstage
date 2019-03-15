package com.example.demo.service;

import com.example.demo.dao.AnswerResultMapper;
import com.example.demo.domain.AnswerResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AnswerResultService {

    @Resource
    private AnswerResultMapper answerResultMapper;

    public int insert (AnswerResult answerResult){
        return answerResultMapper.insert(answerResult);
    }
}
