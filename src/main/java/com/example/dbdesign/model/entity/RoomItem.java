package com.example.dbdesign.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class RoomItem implements Serializable {
    /**
     * 主键
     */
    private long id;

    private long roomId;

    /**
     * 客房号
     */
    private Integer roomNum;

    /**
     * 客房物品名称
     */
    private String itemName;

    /**
     * 物品状态（0-售空，1-正常出售）
     */
    private Integer itemStatus;

    /**
     * 物品单价
     */
    private Integer itemPrice;

    /**
     * 物品数量
     */
    private Integer itemNum;

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
