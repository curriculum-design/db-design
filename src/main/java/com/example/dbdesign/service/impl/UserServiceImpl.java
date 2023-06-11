package com.example.dbdesign.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.dbdesign.common.ErrorCode;
import com.example.dbdesign.exception.BusinessException;
import com.example.dbdesign.mapper.UserMapper;
import com.example.dbdesign.model.dto.UserDTO;
import com.example.dbdesign.model.request.UserLoginRequest;
import com.example.dbdesign.model.request.UserRegisterRequest;
import com.example.dbdesign.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zzs
 * @date 2023/5/21 14:22
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    private static final String SALT = "user";

    @Override
    public Boolean userRegister(UserRegisterRequest user) {
        if (BeanUtil.isEmpty(user)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String nickname = "user_" + IdUtil.fastSimpleUUID();
        user.setNickname(nickname);
        //校验姓名
        String username = user.getUsername();
        if (username == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "姓名不能为空");
        }
        if (username.length() <= 0 || username.length() >= 100) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "姓名长度过长或过短");
        }

        //判断两次输入的密码是否一致
        String password = user.getPassword();
        String checkPassword = user.getCheckPassword();
        if (!checkPassword.equals(password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次密码不一致");
        }

        //密码加密
        String encryptPassword = SecureUtil.md5(password + SALT);
        user.setPassword(encryptPassword);

        //身份证号码校验
        String cardId = user.getCardId();
        if (cardId.length() != 18) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "身份证号码必须为18位");
        }
        String pattern = "^[0-9a-zA-Z]+$";
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(cardId);
        if (!matcher.matches()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "身份证号码只能包含数字或字母");
        }

        //电话号码校验
        String telephone = user.getTelephone();
        if (telephone.length() <= 5 || telephone.length() >= 20) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "电话号码位数错误");
        }
        String pattern1 = "^[-0-9]+$";
        Pattern compile1 = Pattern.compile(pattern1);
        Matcher matcher1 = compile1.matcher(telephone);
        if (!matcher1.matches()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "电话号码只能包含数字和-");
        }
        Integer insertNums = userMapper.saveUser(user);
        if (insertNums == 0) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "向数据库中插入用户信息失败");
        }
        return true;
    }

    @Override
    public UserDTO userLogin(UserLoginRequest userLoginRequest) {
        if (BeanUtil.hasNullField(userLoginRequest)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数不能为空");
        }
        String telephone = userLoginRequest.getTelephone();
        String password = userLoginRequest.getPassword();
        if (telephone.length() >= 20 || password.length() >= 20) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "电话号码或密码过长");
        }
        String pattern = "^[-0-9]+$";
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(telephone);
        if (!matcher.matches()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "电话号码只能保护数字或-");
        }
        String encryptPassword = SecureUtil.md5(password + SALT);
        userLoginRequest.setPassword(encryptPassword);
        UserDTO userDTO = userMapper.queryUser(userLoginRequest);
        if (userDTO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号或密码错误");
        }
        return userDTO;
    }


    public List<UserDTO> getAllUser(){
        return userMapper.getAllUser();
    }

    @Override
    public Boolean deleteUserById(Long userId) {
        if (userId == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        Boolean deleteUserById = userMapper.deleteUserById(userId);
        if (Boolean.FALSE.equals(deleteUserById)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "修改数据库失败");
        }
        return true;
    }
}
