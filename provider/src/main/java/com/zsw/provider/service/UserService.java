package com.zsw.provider.service;

import com.zsw.provider.entity.model.UserOld;

import java.util.List;

/**
 * @author zsw
 * @date 2021/3/8 13:44
 * @description :
 */
//@Service
public interface UserService {
    /**
     * 添加用户
     * @param userOld
     * @return
     */
    int addUser(UserOld userOld);

    /**
     * 更新用户
     * @param userOld
     * @return
     */
    int update(UserOld userOld);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int delete(int id);

    /**
     * 获取用户
     * @return
     */
    List<UserOld> get(int pageNum, int pageSize);

    /**
     * 根据id 查找用户
     */
    UserOld selectById(int id);

}
