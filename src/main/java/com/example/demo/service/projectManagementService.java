package com.example.demo.service;

import com.example.demo.dao.ProjectMapper;
import com.example.demo.domain.Project;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class projectManagementService {
  @Resource
  private ProjectMapper projectMapper;

    /**
     * 查询所有项目
     * @return
     */
  public List<Project> selectAllProject(){
        List<Project> projectList = projectMapper.selectAllProject();
        if(projectList.size() > 0){
            return  projectList;
        }
       return  null;
    }
}
