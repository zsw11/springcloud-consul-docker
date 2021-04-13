package com.zsw.provider.service.impl;


import com.zsw.provider.entity.model.ShardingUser;
import com.zsw.provider.mapper.UserRepository;
import com.zsw.provider.service.ShardingUserService;
import io.shardingsphere.api.HintManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShardingUserServiceImpl implements ShardingUserService {

    @Resource
    UserRepository userRepository;

    @Override
    public Integer addUser(ShardingUser user) {

        // 强制路由主库
//        HintManager.getInstance().setMasterRouteOnly();
        return userRepository.addUser(user);
    }

    @Override
    public List<ShardingUser> list() {

        return userRepository.list();
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public Integer deleteByIds(List<Integer> ids) {
        return userRepository.deleteByIds(ids);
    }
}
