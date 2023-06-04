package com.example.dbdesign.service;

import com.example.dbdesign.model.request.UserOpenRoomRequest;
import com.example.dbdesign.model.request.UserRoomOutRequest;

/**
 * @author zzs
 * @date 2023/5/22 15:34
 */
public interface UserRoomService {

    /**
     * 用户入住接口
     *
     * @param userOpenRoomRequest 用户开房请求
     * @return 是否开房成功
     */
    Boolean userOpenRoom(UserOpenRoomRequest userOpenRoomRequest);

    /**
     * 用户退房接口
     *
     * @param userRoomOutRequest 用户退房请求
     * @return 是否退房成功
     */
    Boolean userOutRoom(UserRoomOutRequest userRoomOutRequest);
}
