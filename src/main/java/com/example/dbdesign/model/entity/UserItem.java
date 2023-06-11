package com.example.dbdesign.model.entity;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class UserItem implements Serializable {
    /**
     * 主键
     */
    private long id;

    /**
     * 用户id
     */
    private long userId;

    /**
     * 物品名称
     */
    private String itemName;

    /**
     * 物品单价
     */
    private long price;
    /**
     * 物品使用数量
     */
    private long itemNumber;

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
