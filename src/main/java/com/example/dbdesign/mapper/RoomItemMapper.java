package com.example.dbdesign.mapper;

import com.example.dbdesign.model.entity.RoomItem;
import com.example.dbdesign.model.request.*;
import org.apache.ibatis.annotations.Param;

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

    List<RoomItem> SearchItemByRoomNum(ItemSearchByRoomNumRequest itemSearchByRoomNumRequest);
    /**
     * 根据数据库里的房间号查询所有的物品信息
     */
    List<RoomItem> queryRoomItem();

    Integer IsExitItemName(@Param("itemName") String ItemName,@Param("roooNum") Integer roomNum);

    Integer ItemExitAdd(@Param("itemNum")Integer ItemNum,@Param("id") Long id);


}
