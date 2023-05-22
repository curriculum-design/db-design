package com.example.dbdesign.model.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户开房请求类
 * @author zzs
 * @date 2023/5/22 15:20
 */
@Data
public class UserOpenRoomRequest implements Serializable {

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 房间 id
     */
    private Long roomId;

    /**
     * 截止时间
     */
    private Date finishTime;

}
