package com.zsw.provider.api;

import com.zsw.provider.entity.ResultResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zsw
 * @date 2021/4/27 16:03
 * @description :
 */
@FeignClient(name = "provider01")
public interface UserApi {
    @GetMapping("/user/get")
    ResultResp getUser(@RequestParam(value = "pageNum") int pageNum,
                       @RequestParam(value = "pageSize") int pageSize);

}
