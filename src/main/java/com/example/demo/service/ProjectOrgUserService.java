package com.example.demo.service;

import com.example.demo.dao.ProjectOrgUserMapper;
import com.example.demo.domain.ProjectOrgUser;
import com.example.demo.domain.ProjectOrgUserExample;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ProjectOrgUserService {

    @Resource
    private ProjectOrgUserMapper projectOrgUserMapper;
    /**
     * 根据项目ID 查询机构
     * @return
     */
    public List<ProjectOrgUser> selectOrgUserByProjectId(String ProjectId){
        ProjectOrgUserExample projectOrgUserExample = new ProjectOrgUserExample();
        projectOrgUserExample.or().andProIdEqualTo(ProjectId);
        return projectOrgUserMapper.selectByExample(projectOrgUserExample);
    }

    /**
     * 根据项目ID 删除数据
     * @return
     */
    public int deleteProjectOrgUser(ProjectOrgUserExample ProjectOrgUserExample){
        return projectOrgUserMapper.deleteByExample(ProjectOrgUserExample);
    }

    /**
     * 根据主键 删除数据
     * @return
     */
    public int deleteProjectOrgUserByPrimetonKey(String key){
        return projectOrgUserMapper.deleteByPrimaryKey(key);
    }

    /**
     * 插入数据
     * @return
     */
    public int insertProjectOrgUser(ProjectOrgUser projectOrgUser){
        try{
            return projectOrgUserMapper.insert(projectOrgUser);
        }catch (Exception e){
            return 0;
        }
    }
}
