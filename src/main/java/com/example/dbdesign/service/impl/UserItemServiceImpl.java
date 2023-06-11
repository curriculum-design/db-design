package com.example.dbdesign.service.impl;
import cn.hutool.core.bean.BeanUtil;
import com.example.dbdesign.common.ErrorCode;
import com.example.dbdesign.exception.BusinessException;
import com.example.dbdesign.model.request.SaveUserItemRequest;
import org.springframework.stereotype.Service;

import com.example.dbdesign.mapper.UserItemMapper;
import com.example.dbdesign.service.UserItemService;

import javax.annotation.Resource;

/**
 * @author 姜楠
 * @date 2023/6/11 15:52
 */

@Service
public class UserItemServiceImpl implements UserItemService {
    @Resource
    private UserItemMapper userItemMapper;
    @Override
    public Boolean saveUserItem(SaveUserItemRequest saveUserItemRequest) {
        if (BeanUtil.hasNullField(saveUserItemRequest)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        return null;
    }

    //下面要判断物品是否

    //private UserItemMapper userItemMapper;

}
