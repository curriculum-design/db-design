package com.example.dbdesign.mapper;

import com.example.dbdesign.model.request.RoomAddRequest;

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

}
