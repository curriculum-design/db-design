package com.example.dbdesign.service;

import com.example.dbdesign.model.entity.Room;
import com.example.dbdesign.model.request.RoomAddRequest;
import com.example.dbdesign.model.request.RoomSearchByRoomNumRequest;
import com.example.dbdesign.model.request.RoomUpdateRequest;

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
     * 进行房间信息的更新
     * @param roomUpdateRequest
     * @return
     */
    Boolean UpdateRoom(RoomUpdateRequest roomUpdateRequest);

    /**
     * 通过房间号进行查询房间信息
     * @param roomSearchByRoomNumRequest
     * @return
     */
    Room queryRoomByRoomNum(RoomSearchByRoomNumRequest roomSearchByRoomNumRequest);

    /**
     * 获取所有房间
     *
     * @return 房间列表
     */
    List<Room> getAllRooms();

}
