package com.example.dbdesign.service;

import com.example.dbdesign.model.entity.RoomItem;
import com.example.dbdesign.model.request.*;

import java.util.List;

public interface RoomItemService {
    /**
     *添加房间物品
     */
    boolean addRoomItem(RoomItemAddRequest roomItemAddRequest);

    boolean DeleteItem(RoomItemDeleteRequest roomItemDeleteRequest);

    boolean UpdateItem(RoomItemUpdateRequest roomItemUpdateRequest);

    List<RoomItem> SearchItemByName(ItemSearchByNameRequest itemSearchByNameRequest);

    List<RoomItem> SearchItemByRoomNumber(ItemSearchByRoomNumRequest itemSearchByRoomNumRequest);

    List<RoomItem> getAllItem();


}
