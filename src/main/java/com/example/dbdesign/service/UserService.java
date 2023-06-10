package com.example.dbdesign.service;

import com.example.dbdesign.model.dto.UserDTO;
import com.example.dbdesign.model.request.UserLoginRequest;
import com.example.dbdesign.model.request.UserRegisterRequest;

import java.util.List;

/**
 * @author zzs
 * @date 2023/5/21 14:22
 */
public interface UserService {

    /**
     * 用户注册接口
     * @param user 用户注册信息
     * @return 是否注册成功
     */
    Boolean userRegister(UserRegisterRequest user);

    /**
     * 用户登录
     *
     * @param userLoginRequest 登录请求（电话号码、密码）
     * @return 用户信息
     */
    UserDTO userLogin(UserLoginRequest userLoginRequest);
    /**
     * 查询所有用户
     * @param
     * @return List<UserDTO>
     * @date
     * @description
     */

    List<UserDTO> getAllUser();


}
