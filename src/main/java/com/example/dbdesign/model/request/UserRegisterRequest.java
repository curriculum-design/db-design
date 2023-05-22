package com.example.dbdesign.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求类
 * @author zzs
 * @date 2023/5/21 16:23
 */
@Data
public class UserRegisterRequest implements Serializable {

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 姓名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 确认密码
     */
    private String checkPassword;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 身份证号码
     */
    private String cardId;

    /**
     * 性别（0-女，1-男）
     */
    private Integer gender;

}
