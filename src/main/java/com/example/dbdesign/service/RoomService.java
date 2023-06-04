package com.example.dbdesign.service;

import com.example.dbdesign.model.entity.Room;
import com.example.dbdesign.model.request.RoomAddRequest;

import java.util.List;

/**
 * @author zzs
 * @date 2023/5/22 11:32
 */
public interface RoomService {

    /**
     * 添加房间
     * @param roomAddRequest 房间添加请求
     * @return 是否添加成功
     */
    Boolean addRoom(RoomAddRequest roomAddRequest);

    /**
     * 获取所有房间
     *
     * @return 房间列表
     */
    List<Room> getAllRooms();

}
