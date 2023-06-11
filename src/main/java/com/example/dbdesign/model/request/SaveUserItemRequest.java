package com.example.dbdesign.model.request;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SaveUserItemRequest implements Serializable{
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
    private long itemPrice;
}
