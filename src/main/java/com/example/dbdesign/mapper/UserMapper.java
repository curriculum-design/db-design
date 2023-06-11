package com.example.dbdesign.mapper;

import com.example.dbdesign.model.dto.UserDTO;
import com.example.dbdesign.model.request.UserLoginRequest;
import com.example.dbdesign.model.request.UserRegisterRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zzs
 * @date 2023/5/21 14:06
 */
public interface UserMapper {

    /**
     * 注册
     *
     * @param userRegisterRequest 用户注册请求
     * @return 受影响条数
     */
    Integer saveUser(UserRegisterRequest userRegisterRequest);

    /**
     * 根据电话号码和密码查询用户
     *
     * @param userLoginRequest 用户登录请求
     * @return 登录信息
     */
    UserDTO queryUser(UserLoginRequest userLoginRequest);

    /**
     * 查询所有用户
     * @param
     * @return List<UserDTO>
     * @date
     * @description
     */
    List<UserDTO> getAllUser();

    /**
     * 根据用户id删除用户
     *
     * @param userId 用户id
     * @return 是否删除成功
     */
    Boolean deleteUserById(@Param("userId") Long userId);

}
