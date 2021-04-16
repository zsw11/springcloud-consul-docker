package com.zsw.provider.controller.outer;

import com.zsw.provider.entity.ResultResp;
import com.zsw.provider.entity.model.UserOld;
import com.zsw.provider.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author zsw
 * @date 2021/3/12 11:22
 * @description :
 */
@RestController
@RequestMapping("/cache")
@Slf4j
public class RedisController {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "分页缓存用户 1~10")
    @GetMapping("user")
    public ResultResp cacheUser() {
        List<UserOld> userOlds = userService.get(1, 10);
        for (UserOld userOld : userOlds) {
            redisTemplate.opsForList().leftPush("user", userOld);
            redisTemplate.expire("user", 3, TimeUnit.HOURS);
        }
        return ResultResp.success("缓存成功");
    }

    @ApiOperation(value = "获取缓存的用户")
    @GetMapping("getUser")
    public ResultResp getUser() {
        UserOld userOld1 = (UserOld)redisTemplate.opsForList().leftPop("user");
        System.out.println(userOld1);
        return ResultResp.success(userOld1);
    }

    @ApiOperation(value = "清理redis缓存", notes="清理redis缓存")
    @RequestMapping(value = "/clearRedis", method = RequestMethod.GET)
    public void clearRedis(){
        // 清除所有的reids缓存
        Set keys = redisTemplate.keys("*");
        redisTemplate.delete(keys);
        log.info("已清除所有的redis缓存!");
    }
}
