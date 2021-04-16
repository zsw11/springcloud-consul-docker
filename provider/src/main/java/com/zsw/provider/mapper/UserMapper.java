package com.zsw.provider.mapper;

import com.zsw.provider.entity.model.UserOld;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zsw
 * @date 2021/3/12 13:50
 * @description :
 */
public interface UserMapper {

    int addUser(UserOld userOld);

    int updateUser(UserOld userOld);

    int delete(@Param("id") int id);

    List<UserOld> get();

    UserOld selectById(@Param("id")int id);
}
