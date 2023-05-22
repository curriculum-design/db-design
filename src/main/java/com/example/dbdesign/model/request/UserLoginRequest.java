package com.example.dbdesign.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求类
 * @author zzs
 * @date 2023/5/21 16:39
 */
@Data
public class UserLoginRequest implements Serializable {

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 密码
     */
    private String password;

}
