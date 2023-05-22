package com.example.dbdesign.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 房间信息表
 * @author zzs
 * @TableName room
 */
@Data
public class Room implements Serializable {
    /**
     * 主键
     */
    private Long id;

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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除（0-正常，1-已删除）
     */
    private Integer isDelete;
}