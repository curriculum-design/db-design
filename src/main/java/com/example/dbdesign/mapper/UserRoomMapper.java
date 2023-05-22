package com.example.dbdesign.mapper;

import com.example.dbdesign.model.request.UserOpenRoomRequest;

/**
 * @author zzs
 * @date 2023/5/22 15:18
 */
public interface UserRoomMapper {

    /**
     * 用户入住，向user-room关联表中插入一条数据
     * @param userOpenRoomRequest 用户入住请求
     * @return 受影响行数
     */
    Integer saveUserRoom(UserOpenRoomRequest userOpenRoomRequest);

}
