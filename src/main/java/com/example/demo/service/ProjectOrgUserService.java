package com.example.demo.service;

import com.example.demo.dao.ProjectOrgUserMapper;
import com.example.demo.domain.ProjectOrgUser;
import com.example.demo.domain.ProjectOrgUserExample;
import com.example.demo.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
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
    public List<HashMap> queryProjectOrgUserByProjectID(String projectID){
        try {
            return projectOrgUserMapper.queryProjectOrgUserByProjectID(projectID);
        }catch (Exception e){
            return null;
        }
    }
    public Boolean saveProjectOrgUsers(List<ProjectOrgUser> list){
        try {
            for(ProjectOrgUser projectOrgUser:list){
                if(StringUtils.isBlank(projectOrgUser.getId())){
                    projectOrgUser.setId(CommonUtil.getPrimaryKey());
                    projectOrgUserMapper.insert(projectOrgUser);
                }else{
                    projectOrgUserMapper.updateByPrimaryKey(projectOrgUser);
                }
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
