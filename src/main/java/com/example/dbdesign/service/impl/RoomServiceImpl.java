package com.example.dbdesign.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.example.dbdesign.common.ErrorCode;
import com.example.dbdesign.exception.BusinessException;
import com.example.dbdesign.mapper.RoomMapper;
import com.example.dbdesign.model.entity.Room;
import com.example.dbdesign.model.request.RoomAddRequest;
import com.example.dbdesign.model.request.RoomSearchByRoomNumRequest;
import com.example.dbdesign.model.request.RoomUpdateRequest;
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
    public Room addRoom(RoomAddRequest roomAddRequest) {

        String roomType = roomAddRequest.getRoomType();
        if (roomType.length() >= 100) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "房间类型过长");
        }
        Integer integer = roomMapper.saveRoom(roomAddRequest);
        if (integer <= 0) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "插入数据库失败");
        }
        Room room = BeanUtil.copyProperties(roomAddRequest, Room.class);
        room.setUpdateTime(room.getCreateTime());
        return room;
    }

    public Boolean UpdateRoom(RoomUpdateRequest roomUpdateRequest){
        if (BeanUtil.hasNullField(roomUpdateRequest)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return roomMapper.UpdateRoom(roomUpdateRequest)>0;
    }

    public Room queryRoomByRoomNum(RoomSearchByRoomNumRequest roomSearchByRoomNumRequest){
        if(BeanUtil.hasNullField(roomSearchByRoomNumRequest)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Integer roomNum = roomSearchByRoomNumRequest.getRoomNumber();
        return roomMapper.queryRoomByRoomNum(roomNum);
    }


    @Override
    public List<Room> getAllRooms() {
        return roomMapper.queryRooms();
    }

    @Override
    public Boolean deleteRoom(Long roomId) {
        if (roomId == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        return roomMapper.deleteRoom(roomId) > 0;
    }
}
