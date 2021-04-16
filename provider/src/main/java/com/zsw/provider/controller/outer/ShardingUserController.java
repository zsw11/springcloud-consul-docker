package com.zsw.provider.controller.outer;

import com.zsw.provider.entity.model.ShardingUser;
import com.zsw.provider.service.ShardingUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class ShardingUserController {

    @Autowired
    private ShardingUserService userService;

    @GetMapping("/usersList")
    public Object list() {
        List<ShardingUser> list= userService.list();
        //Collections.sort(list);
        return list;
    }

    @PostMapping("/add")
    public Object add(@RequestBody ShardingUser shardingUser) {
        userService.addUser(shardingUser);
        // 初始化
//        for(int i=1;i<101;i++) {
//            ShardingUser user = new ShardingUser();
//            user.setId(i);
//            user.setUsername("forezp"+(i));
//            user.setPassword("1233edwd");
//           long resutl = userService.addUser(user);
//            logger.info("insert:"+user.toString()+" result:"+resutl);
//        }
        return "ok";
    }

    @GetMapping("/delete")
    public Object delete() {
    userService.deleteAll();
        return "ok";
    }

    @PostMapping("/deleteById")
    public Object deleteByIds(@RequestParam(value = "ids")Integer[] ids){
        List<Integer> integers = Arrays.asList(ids);
        return userService.deleteByIds(integers);
    }
}
