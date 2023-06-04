package com.example.dbdesign.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.example.dbdesign.common.ErrorCode;
import com.example.dbdesign.exception.BusinessException;
import com.example.dbdesign.mapper.RoomMapper;
import com.example.dbdesign.mapper.UserRoomMapper;
import com.example.dbdesign.model.entity.Room;
import com.example.dbdesign.model.request.UserOpenRoomRequest;
import com.example.dbdesign.model.request.UserRoomOutRequest;
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

    @Resource
    private RoomMapper roomMapper;

    @Override
    public Boolean userOpenRoom(UserOpenRoomRequest userOpenRoomRequest) {
        if (BeanUtil.hasNullField(userOpenRoomRequest)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //判断房间是否存在
        Long roomId = userOpenRoomRequest.getRoomId();
        Room room = roomMapper.queryRoomById(roomId);
        if (BeanUtil.isEmpty(room)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "该房间不存在或房间已被占用");
        }

        return userRoomMapper.saveUserRoom(userOpenRoomRequest) > 0;
    }

    @Override
    public Boolean userOutRoom(UserRoomOutRequest userRoomOutRequest) {
        if (BeanUtil.hasNullField(userRoomOutRequest)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //判断房间状态
        Long roomId = userRoomOutRequest.getRoomId();
        Room room = roomMapper.queryRoomByIdStatus(roomId);
        if (BeanUtil.isEmpty(room)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "该房间不存在或房间未被占用，无法释放");
        }

        return userRoomMapper.outUserRoom(userRoomOutRequest) > 0;
    }
}
