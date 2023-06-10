package com.example.dbdesign.model.request;

import lombok.Data;

import java.io.Serializable;
@Data
public class RoomUpdateRequest implements Serializable {
    /**
     * 房间id
     */

    private Integer id;
    /**
     * 房间号
     */
    private Integer roomNumber;

    /**
     * 房间类型
     */
    private String roomType;

    /**
     * 房间电话
     */
    private String telephone;

    /**
     * 价格/天
     */
    private Integer price;

    /**
     * 房间状态（0-空闲，1-已入住）
     */
    private Integer status;
}
