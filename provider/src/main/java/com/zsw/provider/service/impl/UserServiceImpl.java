package com.zsw.provider.service.impl;

import com.github.pagehelper.PageHelper;
import com.zsw.provider.entity.model.UserOld;
import com.zsw.provider.mapper.UserMapper;
import com.zsw.provider.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zsw
 * @date 2021/3/8 13:45
 * @description :  加入服务降级的功能
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int addUser(UserOld userOld) {
        int i = userMapper.addUser(userOld); //这里可以直接传对象
        return userOld.getId(); //返回新增数据的id
    }

    @Override
    public int update(UserOld userOld) {
        userMapper.updateUser(userOld);
        return userOld.getId();

    }

    @Override
    public int delete(int id) {
        int delete = userMapper.delete(id);
        return delete;
    }

    @Override
    public List<UserOld> get(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
       return userMapper.get();
    }

    @Override
    public UserOld selectById(int id) {
        return userMapper.selectById(id);
    }

}
