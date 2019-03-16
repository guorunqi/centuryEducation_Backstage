package com.example.demo.service;

import com.example.demo.dao.ProjectPolicyDocumentMapper;
import com.example.demo.domain.ProjectPolicyDocumentExample;
import com.example.demo.domain.ProjectPolicyDocumentKey;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import java.util.List;

@Controller
public class ProjectPolicyDocumentService {
    @Resource
    private ProjectPolicyDocumentMapper projectPolicyDocumentMapper;

    /**
     * 插入一条数据
     * @param projectPolicyDocumentKey
     * @return
     */
    public int insertProjectPolicyDocument(ProjectPolicyDocumentKey projectPolicyDocumentKey){
        try{
            int i = projectPolicyDocumentMapper.insert(projectPolicyDocumentKey);
            return  i;
        }catch (Exception e){
            return  0;
        }


    }

    /**
     * 根据项目ID 查询 项目文件关系表
     * @param projectId
     * @return
     */
    public List<ProjectPolicyDocumentKey> selectPolicyDocumentByProjectId(String projectId){
        ProjectPolicyDocumentExample projectPolicyDocumentExample = new ProjectPolicyDocumentExample();
        projectPolicyDocumentExample.or().andProjectIdEqualTo(projectId);
        return projectPolicyDocumentMapper.selectByExample(projectPolicyDocumentExample);
    }

    /**
     * 删除一条数据
     * @param projectId
     * @return
     */
    public  int deleteProjectPolicyDocument(String projectId){
        ProjectPolicyDocumentExample projectPolicyDocumentExample = new ProjectPolicyDocumentExample();
        projectPolicyDocumentExample.or().andProjectIdEqualTo(projectId);
        return projectPolicyDocumentMapper.deleteByExample(projectPolicyDocumentExample);
    }

    /**
     * 删除一条数据
     * @param projectId
     * @return
     */
    public  int deleteProjectPolicyDocumentByExample(ProjectPolicyDocumentExample projectPolicyDocumentExample){
        return projectPolicyDocumentMapper.deleteByExample(projectPolicyDocumentExample);
    }


}
