package com.example.dbdesign.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户房间关联表
 * @author zzs
 * @TableName user_room
 */
@Data
public class UserRoom implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 房间id
     */
    private Long roomId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 入住截止时间
     */
    private Date finishTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除（0-正常，1-已删除）
     */
    private Integer isDelete;
}