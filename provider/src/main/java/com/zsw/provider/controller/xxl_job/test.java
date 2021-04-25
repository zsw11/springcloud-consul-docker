package com.zsw.provider.controller.xxl_job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Controller;

/**
 * @author zsw
 * @date 2021/4/23 18:14
 * @description :
 */
@Controller
public class test {
    @XxlJob("helloJob")
    public ReturnT<String> execute(String param) throws Exception{
        XxlJobLogger.log("hello" + param);
//        int a = 1 / 0;
        System.out.println("hello" + param);
        return ReturnT.SUCCESS;
    }

}
