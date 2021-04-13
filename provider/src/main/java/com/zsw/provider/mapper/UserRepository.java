package com.zsw.provider.mapper;


import com.zsw.provider.entity.model.ShardingUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRepository {

    Integer addUser(ShardingUser user);

    List<ShardingUser> list();

    void deleteAll();

    Integer deleteByIds(@Param("ids") List<Integer> ids);

}

