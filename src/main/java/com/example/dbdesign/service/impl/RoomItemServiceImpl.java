package com.example.dbdesign.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.example.dbdesign.common.ErrorCode;
import com.example.dbdesign.exception.BusinessException;
import com.example.dbdesign.mapper.RoomItemMapper;
import com.example.dbdesign.model.entity.RoomItem;
import com.example.dbdesign.model.request.RoomItemAddRequest;
import com.example.dbdesign.model.request.RoomItemDeleteRequest;
import com.example.dbdesign.model.request.RoomItemUpdateRequest;
import com.example.dbdesign.service.RoomItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoomItemServiceImpl implements RoomItemService {
    @Resource
    private RoomItemMapper roomItemMapper;

    @Override
    public boolean addRoomItem(RoomItemAddRequest roomItemAddRequest) {
        if(BeanUtil.hasNullField(roomItemAddRequest)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        Integer itemPrice = roomItemAddRequest.getItemPrice();
        if(itemPrice < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"物品单价不能小于0");
        }

        Integer itemNum = roomItemAddRequest.getItemNum();
        if(itemNum < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"物品数量不能小于0");
        }

        return roomItemMapper.saveItem(roomItemAddRequest)>0;

    }

    public boolean DeleteItem(RoomItemDeleteRequest roomItemDeleteRequest){
        if(BeanUtil.hasNullField(roomItemDeleteRequest)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        return roomItemMapper.DeleteItem(roomItemDeleteRequest)>0;
    }

    public boolean UpdateItem(RoomItemUpdateRequest roomItemUpdateRequest){
        if(BeanUtil.hasNullField(roomItemUpdateRequest)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Integer itemPrice = roomItemUpdateRequest.getItemPrice();
        if(itemPrice < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"物品单价不能小于0");
        }

        Integer itemNum = roomItemUpdateRequest.getItemNum();
        if(itemNum < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"物品数量不能小于0");
        }

        return roomItemMapper.UpdateItem(roomItemUpdateRequest)>0;
    }

    @Override
    public List<RoomItem> getAllItem() {
        return roomItemMapper.queryRoomItem();
    }
}
