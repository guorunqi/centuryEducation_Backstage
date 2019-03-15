package com.example.demo.service;

import com.example.demo.dao.OrgUserMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.domain.OrgUser;
import com.example.demo.domain.OrgUserExample;
import com.example.demo.domain.User;
import com.example.demo.domain.UserExample;
import com.example.demo.util.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private OrgUserMapper orgUserMapper;
    /**
     * 查询所有专家
     * @return
     */
    public List<User> selectAllSpecialist(){
        UserExample userExample = new UserExample();
        userExample.or().andRoleIdEqualTo("3");
        List<User> UserList = userMapper.selectByExample(userExample);
        if(UserList.size() > 0) return UserList;
        return  null;
    }

    /**
     * 根据UserId查询专家
     * @return
     */
    public User selectSpecialistById(String id){
        return userMapper.selectByPrimaryKey(id);
    }

    public List<User> queryUserByOrgId(String id){
        return userMapper.queryUserByOrgID(id);
    }

    public Boolean saveUserAndOrgUser(User user, OrgUser orgUser){
        if(user.getId()!=null&&!user.getId().equals("")){
            userMapper.updateByPrimaryKeySelective(user);
        }else {
            user.setId(CommonUtil.getPrimaryKey());
            userMapper.insert(user);
            orgUser.setUserId(user.getId());
            orgUser.setId(CommonUtil.getPrimaryKey());
            orgUserMapper.insert(orgUser);
        }
        return true;
    }
    public Boolean deleteUserAndOrgUser(List<User> users,String orgid){
        try {
            if(users!=null&&users.size()>0){
                for(User user:users){
                    OrgUserExample example=new OrgUserExample();
                    example.or().andOrgIdEqualTo(orgid).andUserIdEqualTo(user.getId());
                    orgUserMapper.deleteByExample(example);
                    userMapper.deleteByPrimaryKey(user.getId());
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
