package com.example.demo.service;

import com.example.demo.dao.ProjectOrgMapper;
import com.example.demo.domain.ProjectOrg;
import com.example.demo.domain.ProjectOrgExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
public class ProjectOrgService {

    @Resource
    private ProjectOrgMapper projectOrgMapper;
    /**
     * 插入数据
     * @return
     */
    public int insertProjectOrg(ProjectOrg projectOrg){
        int i = projectOrgMapper.insert(projectOrg);
        return  i;
    }

    /**
     * 根据项目ID 查询机构
     * @return
     */
    public List<ProjectOrg> selectOrgByProjectId(String ProjectId){
        ProjectOrgExample projectOrgExample = new ProjectOrgExample();
        projectOrgExample.or().andProIdEqualTo(ProjectId);
        List<ProjectOrg> ProjectOrgs = projectOrgMapper.selectByExample(projectOrgExample);
        if (ProjectOrgs.size() > 0){
            return ProjectOrgs;
        }
        return  null;
    }

    /**
     * 根据项目ID 删除数据
     * @return
     */
    public int deleteOrgByProjectId(String ProjectId){
        ProjectOrgExample projectOrgExample = new ProjectOrgExample();
        projectOrgExample.or().andProIdEqualTo(ProjectId);
        return projectOrgMapper.deleteByExample(projectOrgExample);
    }
    public List<HashMap> queryProjectOrgByProjectID(String projectID){
        if(!StringUtils.isBlank(projectID)){
            return projectOrgMapper.queryProjectOrgByProjectID(projectID);
        }else{
            return null;
        }
    }
    public List<HashMap> queryProjectOrgByProjectIDAndQuotaID(String projectID,String quotaID){
        if(!StringUtils.isBlank(projectID)){
            return projectOrgMapper.queryProjectOrgByProjectIDAndQuotaID(projectID,quotaID);
        }else{
            return null;
        }
    }
}
