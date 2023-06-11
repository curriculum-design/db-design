package com.example.dbdesign.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class ItemSearchByRoomNumRequest implements Serializable {
    /**
     * 物品房间号
     */
    private Integer roomNumber;

}
