package com.example.demo.service;

import com.example.demo.dao.ProjectMapper;
import com.example.demo.domain.Project;
import com.example.demo.domain.ProjectExample;
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
        List<Project> projectList = projectMapper.selectByExample(new ProjectExample());
        if(projectList.size() > 0){
            return  projectList;
        }
       return  null;
    }

    /**
     * 插入一条项目表数据
     * @param project
     * @return
     */
    public int insertProject(Project project){
        int i = projectMapper.insert(project);
        if(i > 0){
            return  i;
        }
        return  0;
    }

    /**
     * 更新一条项目表数据
     * @param project
     * @return
     */
    public int upDataProject(Project project){
        return projectMapper.updateByPrimaryKey(project);
    }

    /**
     * 根据模板查询一条数据
     * @param projectExample
     * @return
     */
    public Project selectProjectByExample(ProjectExample projectExample){
        List<Project> list = projectMapper.selectByExample(projectExample);
        if(list.size() ==  1){
            return  list.get(1);
        }
        return  null;
    }

    /**
     * 根据id查询一条数据
     * @param id
     * @return
     */
    public Project selectProjectByPrimaryKey(String id){
        return projectMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据项目ID 查询一条数据
     * @param projectId
     * @return
     */
    public Project selectProjectByProjectId(String projectId){
        return  projectMapper.selectByPrimaryKey(projectId);
    }

    /**
     * 根据项目ID删除一条数据
     * @param projectId
     * @return
     */
    public int deleteProjectByProjectId(String projectId){
        return  projectMapper.deleteByPrimaryKey(projectId);
    }

    /**
     * 根据项目ID 查询一条数据
     */
    public List<Project> selectByCondition(String name,String classOne,String status,String classTwo,String orgs){
        return  projectMapper.selectByCondition(name,classOne,status,classTwo,orgs);
    }
}
