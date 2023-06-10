package com.example.dbdesign.service;

import com.example.dbdesign.model.entity.RoomItem;
import com.example.dbdesign.model.request.ItemSearchByNameRequest;
import com.example.dbdesign.model.request.RoomItemAddRequest;
import com.example.dbdesign.model.request.RoomItemDeleteRequest;
import com.example.dbdesign.model.request.RoomItemUpdateRequest;

import java.util.List;

public interface RoomItemService {
    /**
     *添加房间物品
     */
    boolean addRoomItem(RoomItemAddRequest roomItemAddRequest);

    boolean DeleteItem(RoomItemDeleteRequest roomItemDeleteRequest);

    boolean UpdateItem(RoomItemUpdateRequest roomItemUpdateRequest);

    List<RoomItem> SearchItemByName(ItemSearchByNameRequest itemSearchByNameRequest);

    List<RoomItem> getAllItem();
}
