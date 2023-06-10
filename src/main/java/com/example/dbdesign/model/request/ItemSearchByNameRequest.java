package com.example.dbdesign.model.request;

import lombok.Data;

import java.io.Serializable;
@Data
public class ItemSearchByNameRequest implements Serializable {
    /**
     * 物品名称
     */
    private String itemName;

    private String id;

}
