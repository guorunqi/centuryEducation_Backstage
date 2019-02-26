package com.example.demo.service;

import com.example.demo.dao.OrganizationMapper;
import com.example.demo.dao.ProblemMapper;
import com.example.demo.domain.Organization;
import com.example.demo.domain.OrganizationExample;
import com.example.demo.domain.Problem;
import com.example.demo.domain.ProblemExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProblemService {
@Resource
private ProblemMapper problemMapper;

    public int insertProblem(Problem problem){
      return problemMapper.insert(problem);
    }

    public List<Problem> selectByExample(String QuestionnaireId){
        ProblemExample problemExample = new ProblemExample();
        problemExample.or().andQuestionnaireIdEqualTo(QuestionnaireId);
        return problemMapper.selectByExample(problemExample);
    }

    public int updateByPrimaryKey( Problem problem){
        return problemMapper.updateByPrimaryKey(problem);
    }

    public int deleteByPrimaryKey( String id){
        return problemMapper.deleteByPrimaryKey(id);
    }
}
