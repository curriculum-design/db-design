package com.example.dbdesign.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.example.dbdesign.common.ErrorCode;
import com.example.dbdesign.exception.BusinessException;
import com.example.dbdesign.mapper.UserRoomMapper;
import com.example.dbdesign.model.request.UserOpenRoomRequest;
import com.example.dbdesign.service.UserRoomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zzs
 * @date 2023/5/22 15:35
 */
@Service
public class UserRoomServiceImpl implements UserRoomService {

    @Resource
    private UserRoomMapper userRoomMapper;

    @Override
    public Boolean userOpenRoom(UserOpenRoomRequest userOpenRoomRequest) {
        if (BeanUtil.hasNullField(userOpenRoomRequest)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        return userRoomMapper.saveUserRoom(userOpenRoomRequest) > 0;
    }
}
