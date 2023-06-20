package com.example.dbdesign.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zzs
 * @date 2023/6/19 16:18
 */
@Data
public class ItemConsumeInfo implements Serializable {

    /**
     * 物品id
     */
    private Long itemId;

    /**
     * 物品名
     */
    private String itemName;

    /**
     * 消耗数量
     */
    private Integer count;

    /**
     * 单价
     */
    private Integer price;

    /**
     * 总价
     */
    private Integer totalPrice;

}
