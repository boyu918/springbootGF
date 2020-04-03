package com.zby.manage;


import com.zby.manage.fallback.FallBackController;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author zby
 * @time 2019/6/6 15:02
 */
@FeignClient(value = "zby-springboot",fallback = FallBackController.class)
public interface UserApi {
    @PostMapping("/user/hello")
    String hello();
}
