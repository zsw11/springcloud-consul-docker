package com.zsw.provider.controller.inner;


import com.zsw.provider.controller.api.UserApi;
import com.zsw.provider.entity.ResultResp;
import com.zsw.provider.entity.model.ShardingUser;
import com.zsw.provider.entity.model.UserOld;
import com.zsw.provider.service.ShardingUserService;
import com.zsw.provider.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zsw
 * @date 2021/3/8 11:41
 * @description : user
 */
@RestController
@RequestMapping("/user")
@Api("用户信息")
@Slf4j
public class UserController {

    @Resource
    private UserService UserService;

    @Resource
    private ShardingUserService shardingUserService;

    @Autowired
    TransactionTemplate transactionTemplate;//引入事务管理器

    @ApiOperation(value = "添加用户")
    @PostMapping("/add")
    public ResultResp addUser(@Validated @RequestBody UserOld userOld, BindingResult bingingResult) { //@Validated 表示检验User 对象， BindingResult 表示检验出错是保存的错误信息,BindingResult 要跟在实体类之后,
        ArrayList<String> result = new ArrayList<>();
        if (bingingResult.hasErrors()) {
            List<ObjectError> allErrors = bingingResult.getAllErrors();
            for (ObjectError error : allErrors) {
                result.add(error.getDefaultMessage());
            }
            return ResultResp.fail(result);
        }
        // 返回插入数据的id
        int id = UserService.addUser(userOld);
        if (id > 0) {
            return ResultResp.success(id);
        }
        return ResultResp.fail(id);
    }

    @ApiOperation("更新用户")
    @PutMapping("/update")
    public ResultResp updateUser(@Validated @RequestBody UserOld userOld) {
        UserOld userOldById = UserService.selectById(userOld.getId());
        if (userOldById == null) {
            throw new RuntimeException("id:" + userOld.getId() + "不存在该用户，找不到信息");
        }
        int update = UserService.update(userOld);
        return ResultResp.success(update);
    }

    @ApiOperation("删除用户")
    @ApiImplicitParam(name = "id", value = "用户id")
    @DeleteMapping("/delete/{id}")
    public ResultResp deleteUser(@PathVariable int id) {
        int delete = UserService.delete(id);
        if (delete <= 0) {
            throw new RuntimeException("id:" + id + "不存在该用户，找不到信息");
        }
        return ResultResp.success(delete);
    }

    @ApiOperation("分页获取用户")
    @GetMapping("/get")
    public ResultResp getUser(@RequestParam int pageNum, @RequestParam int pageSize) {
        List<UserOld> userOldList = UserService.get(pageNum, pageSize);
        return ResultResp.success(userOldList);
    }

    @ApiOperation("测试编程式事务")
    @PostMapping("/addUserAndShardingUser")
    public ResultResp UpdateUserTest(@Validated @RequestBody UserOld userOld) {
        //开启事务保存数据
        boolean result = transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    //        // 返回插入数据的id
                    int id = UserService.addUser(userOld);
                    ShardingUser shardingUser = new ShardingUser();
                    shardingUser.setId(id+221);
                    shardingUser.setUsername("zsw分布式事务测试");
                    shardingUser.setPassword("123456");
                    shardingUserService.addUser(shardingUser);
                } catch (Exception e) {
//                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //手动开启事务回滚
                    status.setRollbackOnly(); //回滚
                    log.error(e.getMessage(), e);
                    return false;
                }
                return true;
            }
        });
        return ResultResp.success(result);
    }

}
