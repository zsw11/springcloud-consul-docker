//package com.zsw.provider.controller.api.fallback;
//
//import com.zsw.provider.controller.api.UserApi;
//import com.zsw.provider.entity.ResultResp;
//import feign.hystrix.FallbackFactory;
//import org.springframework.stereotype.Component;
//
///**
// * @author zsw
// * @date 2021/4/27 16:04
// * @description :
// */
//@Component
//public class UserApiFallback implements FallbackFactory<UserApi> {
//    @Override
//    public UserApi create(Throwable throwable) {
//        return new UserApi() {
//            @Override
//            public ResultResp getUser(int pageNum, int pageSize) {
//                return ResultResp.exception("服务熔断了");
//            }
//        };
//    }
//}
