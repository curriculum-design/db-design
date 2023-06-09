package com.example.dbdesign.model.request;

import lombok.Data;

import java.io.Serializable;
@Data
public class RoomItemUpdateRequest implements Serializable {
    /**
     * 物品的id
     */
    private Integer id;

    /**
     * 房号
     */

    private Integer roomNumber;
    /**
     * 物品名称
     */

    private String itemName;
    /**
     * 物品状态
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
}
