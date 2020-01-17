package com.zby.manage.model;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zby
 * @time 2019/6/12 10:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserJwt {
    String Id;
    String username;
    String password;
    public static String getToken (UserJwt userJwt){
        String token="";
        token= JWT.create().withAudience(userJwt.getId())
                .sign(Algorithm.HMAC256(userJwt.getPassword()));
        return token;
    }
}
