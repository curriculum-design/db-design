package com.example.dbdesign.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.dbdesign.common.BaseResponse;
import com.example.dbdesign.common.ErrorCode;
import com.example.dbdesign.common.ResultUtils;
import com.example.dbdesign.exception.BusinessException;
import com.example.dbdesign.model.entity.RoomItem;
import com.example.dbdesign.model.request.RoomItemAddRequest;
import com.example.dbdesign.model.request.RoomItemDeleteRequest;
import com.example.dbdesign.model.request.RoomItemUpdateRequest;
import com.example.dbdesign.service.RoomItemService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
@RestController
@RequestMapping("/Item")
public class RoomItemController {

    @Resource
    public RoomItemService roomItemService;

    @PostMapping("/addItem")
    public BaseResponse<Boolean> addRoomItem(@RequestBody RoomItemAddRequest roomItemAddRequest){
        if (BeanUtil.isEmpty(roomItemAddRequest)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Boolean addItem = roomItemService.addRoomItem(roomItemAddRequest);
        if(Boolean.FALSE.equals(addItem)){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"添加物品失败");
        }
        return ResultUtils.success(true,"添加物品成功");
    }
    @PostMapping("/deleteItem")
    public BaseResponse<Boolean> deleteRoomItem(@RequestBody RoomItemDeleteRequest roomItemDeleteRequest){
        if(BeanUtil.isEmpty(roomItemDeleteRequest)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Boolean DeleteItem = roomItemService.DeleteItem(roomItemDeleteRequest);
        if(Boolean.FALSE.equals(DeleteItem)){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"删除物品失败");
        }
        return ResultUtils.success(true,"删除物品成功");
    }
    @PostMapping("/updateItem")
    public BaseResponse<Boolean> UpdateRoomItem(@RequestBody RoomItemUpdateRequest roomItemUpdateRequest){
        if(BeanUtil.isEmpty(roomItemUpdateRequest)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Boolean UpdateItem = roomItemService.UpdateItem(roomItemUpdateRequest);
        if(Boolean.FALSE.equals(UpdateItem)){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"更新物品失败");
        }
        return ResultUtils.success(true,"更新物品成功");
    }


    @GetMapping("/getItem")
    public BaseResponse<List<RoomItem>> getAllItem(){
        List<RoomItem> roomItemList = roomItemService.getAllItem();
        return ResultUtils.success(roomItemList,"获取房间物品的信息列表成功");
    }


}
