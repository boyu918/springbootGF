package com.zby.manage;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zby
 * @time 2019/6/6 15:02
 */
@FeignClient(value = "zby-springboot")
public interface UserApi {
    @GetMapping("user/hello")
    String hello();

}
