package com.example.dbdesign.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zzs
 * @date 2023/6/19 16:33
 */
@Data
public class RoomConsumeInfo implements Serializable {

    /**
     * 房间id
     */
    private Long roomId;

    /**
     * 房间号
     */
    private Integer roomNumber;

    /**
     * 房间类型
     */
    private String roomType;

    /**
     * 天数
     */
    private Integer days;

    /**
     * 单价
     */
    private Integer price;

    /**
     * 总价
     */
    private Integer totalPrice;
}
