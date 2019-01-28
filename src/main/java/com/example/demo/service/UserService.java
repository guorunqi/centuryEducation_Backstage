package com.example.demo.service;

import com.example.demo.dao.UserMapper;
import com.example.demo.domain.User;
import com.example.demo.domain.UserExample;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserService {

    @Resource
    private UserMapper userMapper;
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
}
