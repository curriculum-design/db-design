package com.example.dbdesign.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Bill  implements Serializable{
    /**
     * 主键
     */
    private long id;

    /**
     * 客房id
     */
    private long roomId;

    /**
     * 用户id
     */
    private long userId;

    /**
     * 支付状态是否支付（0-已经支付，1-未支付）
     */
    private Integer isPay;


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
