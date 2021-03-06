package com.example.demo.service;

import com.example.demo.dao.AnswerResultMapper;
import com.example.demo.domain.AnswerResult;
import com.example.demo.domain.AnswerResultExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AnswerResultService {

    @Resource
    private AnswerResultMapper answerResultMapper;

    public int insert (AnswerResult answerResult){
        return answerResultMapper.insert(answerResult);
    }
    public int updateByPrimaryKey (AnswerResult answerResult){
        return answerResultMapper.updateByPrimaryKey(answerResult);
    }
    public int deleteByPrimaryKey (String id){
        return answerResultMapper.deleteByPrimaryKey(id);
    }
    public List<AnswerResult> selectByExample (AnswerResultExample answerResultExample){
        return answerResultMapper.selectByExample(answerResultExample);
    }
}
