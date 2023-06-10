package com.example.dbdesign.mapper;

import com.example.dbdesign.model.entity.RoomItem;
import com.example.dbdesign.model.request.ItemSearchByNameRequest;
import com.example.dbdesign.model.request.RoomItemAddRequest;
import com.example.dbdesign.model.request.RoomItemDeleteRequest;
import com.example.dbdesign.model.request.RoomItemUpdateRequest;

import java.util.List;

public interface RoomItemMapper {

    /**
     * 添加物品
     * @param roomItemAddRequest
     * @return
     */
    Integer saveItem(RoomItemAddRequest roomItemAddRequest);

    /**
     * 删除物品
     * @param roomItemDeleteRequest
     * @return
     */
    Integer DeleteItem(RoomItemDeleteRequest roomItemDeleteRequest);

    /**
     * 更新物品信息
     * @param roomItemUpdateRequest
     * @return
     */
    Integer UpdateItem(RoomItemUpdateRequest roomItemUpdateRequest);

    /**
     * 通过物品名称查询物品
     * @param itemSearchByNameRequest
     * @return
     */
    List<RoomItem> SearchItemByName(ItemSearchByNameRequest itemSearchByNameRequest);

    /**
     * 根据数据库里的房间号查询所有的物品信息
     */
    List<RoomItem> queryRoomItem();
}
