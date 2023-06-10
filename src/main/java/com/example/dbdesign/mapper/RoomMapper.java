package com.example.dbdesign.mapper;

import com.example.dbdesign.model.entity.Room;
import com.example.dbdesign.model.request.RoomAddRequest;
import com.example.dbdesign.model.request.RoomUpdateRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zzs
 * @date 2023/5/22 11:26
 */
public interface RoomMapper {

    /**
     * 管理员添加房间
     * @param roomAddRequest 添加房间请求
     * @return 受影响行数
     */
    Integer saveRoom(RoomAddRequest roomAddRequest);

    Integer UpdateRoom(RoomUpdateRequest roomUpdateRequest);

    /**
     * 根据房间号来查询房间
     * @param roomNumber 房间号
     * @return 房间信息
     */
    Room queryRoomByRoomNum(@Param("roomNumber") Integer roomNumber);

    /**
     * 根据房间id来查询房间信息
     * @param id 房间id
     * @return 房间信息
     */
    Room queryRoomById(@Param("id") Long id);

    /**
     * 根据id查询房间，并判断房间是否为占用状态
     * @param id 房间id
     * @return 房间信息
     */
    Room queryRoomByIdStatus(@Param("id") Long id);

    /**
     * 查询所有房间
     *
     * @return 房间列表
     */
    List<Room> queryRooms();

}
