package com.zsw.provider.service;


import com.zsw.provider.entity.model.ShardingUser;

import java.util.List;

public interface ShardingUserService {

    Integer addUser(ShardingUser user);

    List<ShardingUser> list();

    void deleteAll();

    Integer deleteByIds(List<Integer> ids);
}
