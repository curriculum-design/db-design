package com.example.dbdesign.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zzs
 * @date 2023/5/22 19:41
 */
@Data
public class UserRoomOutRequest implements Serializable {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 房间id
     */
    private Long roomId;

}
