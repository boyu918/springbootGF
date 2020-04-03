package com.zby.manage.fallback;

import com.zby.manage.UserApi;
import org.springframework.stereotype.Component;

/**
 * Description:
 * User: 小卡掌柜
 * Date: 2020-04-02
 * Time: 5:12 PM
 */
@Component
public class FallBackController implements UserApi{
    @Override
    public String hello() {
        return "this is fallback controller";
    }
}
