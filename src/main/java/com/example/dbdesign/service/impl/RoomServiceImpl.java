package com.example.dbdesign.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.example.dbdesign.common.ErrorCode;
import com.example.dbdesign.exception.BusinessException;
import com.example.dbdesign.mapper.RoomMapper;
import com.example.dbdesign.model.entity.Room;
import com.example.dbdesign.model.request.RoomAddRequest;
import com.example.dbdesign.service.RoomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zzs
 * @date 2023/5/22 11:38
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Resource
    private RoomMapper roomMapper;

    @Override
    public Boolean addRoom(RoomAddRequest roomAddRequest) {
        if (BeanUtil.hasNullField(roomAddRequest)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String roomType = roomAddRequest.getRoomType();
        if (roomType.length() >= 100) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "房间类型过长");
        }

        return roomMapper.saveRoom(roomAddRequest) > 0;
    }

    @Override
    public List<Room> getAllRooms() {
        return roomMapper.queryRooms();
    }
}
