package com.example.dbdesign.mapper;

import com.example.dbdesign.model.request.UserOpenRoomRequest;
import com.example.dbdesign.model.request.UserRoomOutRequest;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 用户退房
     * @param userRoomOutRequest 用户退房请求
     * @return 受影响行数
     */
    Integer outUserRoom(UserRoomOutRequest userRoomOutRequest);

    /**
     * 根据房间id获取开房用户的id
     * @param roomId 房间id
     * @return 用户id
     */
    Long getUserId(@Param("roomId") Long roomId);

}
