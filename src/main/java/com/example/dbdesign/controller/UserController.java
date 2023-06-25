package com.example.dbdesign.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.dbdesign.annotation.RoleCheck;
import com.example.dbdesign.common.BaseResponse;
import com.example.dbdesign.common.ErrorCode;
import com.example.dbdesign.common.ResultUtils;
import com.example.dbdesign.exception.BusinessException;
import com.example.dbdesign.model.dto.UserDTO;
import com.example.dbdesign.model.request.UserLoginRequest;
import com.example.dbdesign.model.request.UserRegisterRequest;
import com.example.dbdesign.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zzs
 * @date 2023/5/21 16:19
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    public static final String SESSION_KEY = "user_login_state";
    /**
     * 用户注册接口
     * @param user 用户注册请求
     * @return 是否注册成功
     */
    @PostMapping("/register")
    public BaseResponse<Boolean> userRegister(@RequestBody UserRegisterRequest user) {
        if (BeanUtil.isEmpty(user)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Boolean isRegister = userService.userRegister(user);
        if (Boolean.FALSE.equals(isRegister)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "用户注册失败");
        }
        return ResultUtils.success(true, "注册成功");
    }

    /**
     * 用户登录接口
     * @param userLoginRequest 登录请求
     * @param request servlet请求
     * @return 登录用户信息
     */
    @PostMapping("/login")
    public BaseResponse<UserDTO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (BeanUtil.hasNullField(userLoginRequest)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserDTO userDTO = userService.userLogin(userLoginRequest);
        //保存登录态
        request.getSession().setAttribute(SESSION_KEY, userDTO);
        return ResultUtils.success(userDTO, "登录成功");
    }

    @RoleCheck
    @PostMapping("/GetAllUser")
    public BaseResponse<List<UserDTO>> GetAllUser(){
        List<UserDTO> getAllUser = userService.getAllUser();
        return ResultUtils.success(getAllUser,"获取所以用户成功");
    }

    @RoleCheck
    @DeleteMapping("/deleteUserById")
    public BaseResponse<Boolean> deleteUserById(Long userId, HttpServletRequest request) {
        if (userId == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        Boolean aBoolean = userService.deleteUserById(userId);
        if (Boolean.FALSE.equals(aBoolean)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "删除用户失败");
        }
        return ResultUtils.success(true, "删除用户成功");
    }

}
