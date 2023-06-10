package com.example.dbdesign.mapper;

import com.example.dbdesign.model.entity.RoomItem;
import com.example.dbdesign.model.request.ItemSearchByNameRequest;
import com.example.dbdesign.model.request.RoomItemAddRequest;
import com.example.dbdesign.model.request.RoomItemDeleteRequest;
import com.example.dbdesign.model.request.RoomItemUpdateRequest;

import java.util.List;

public interface RoomItemMapper {
    /**
     * 管理员添加物品
     */

    Integer saveItem(RoomItemAddRequest roomItemAddRequest);

    Integer DeleteItem(RoomItemDeleteRequest roomItemDeleteRequest);

    Integer UpdateItem(RoomItemUpdateRequest roomItemUpdateRequest);

    List<RoomItem> SearchItemByName(ItemSearchByNameRequest itemSearchByNameRequest);

    /**
     * 根据数据库里的房间号查询所有的物品信息
     */
    List<RoomItem> queryRoomItem();
}
