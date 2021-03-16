package com.zsw.comsumer.service.hystric;

import com.zsw.comsumer.entity.ResultResp;
import com.zsw.comsumer.entity.model.User;
import com.zsw.comsumer.service.UserService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author zsw
 * @date 2021/3/11 14:17
 * @description : 服务熔断降级 处理    这里需要降低 springcloud 版本 ，要不然 导入的FallbackFactory 包不对 ，大坑
 */
@Component
public class UserServiceHystrix implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable cause) {
        return new UserService() {

            @Override
            public ResultResp addUser(User user) {
                System.out.println("服务熔断降级");
                return ResultResp.fail("服务熔断降级");
            }

            @Override
            public ResultResp update(User user) {
                System.out.println("服务熔断降级");
                return ResultResp.fail("服务熔断降级：不存在这个用户id:"+ user.getId());
            }

            @Override
            public ResultResp delete(int id) {
                System.out.println("服务熔断降级");
                return ResultResp.fail("服务熔断降级：不存在这个用户id:"+ id);
            }

            @Override
            public ResultResp get(int pageNum,int pageSize) {
                System.out.println("服务熔断降级");
                return null;
            }
        };
    }
}
