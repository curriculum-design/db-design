package com.example.dbdesign.model.request;

import lombok.Data;

import java.io.Serializable;
@Data
public class RoomSearchByRoomNumRequest implements Serializable {
    /**
     * 根据房间号查询所有的信息
     */
    private Integer roomNumber;
}
