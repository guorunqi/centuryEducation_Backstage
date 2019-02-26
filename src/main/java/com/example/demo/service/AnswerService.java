package com.example.demo.service;

import com.example.demo.dao.AnswerMapper;
import com.example.demo.domain.Answer;
import com.example.demo.domain.AnswerExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AnswerService {
@Resource
private AnswerMapper answerMapper;

  public int insertAnswer(Answer answer){
      return answerMapper.insert(answer);
    }

    public List<Answer> selectByExample(AnswerExample answerExample){
        return answerMapper.selectByExample(answerExample);
    }

    public int updateByPrimaryKey(Answer answer){
        return answerMapper.updateByPrimaryKey(answer);
    }

    public int deleteByPrimaryKey(String id){
        return answerMapper.deleteByPrimaryKey(id);
    }
}
