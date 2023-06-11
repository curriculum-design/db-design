package com.example.dbdesign.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.dbdesign.common.BaseResponse;
import com.example.dbdesign.common.ErrorCode;
import com.example.dbdesign.common.ResultUtils;
import com.example.dbdesign.exception.BusinessException;
import com.example.dbdesign.model.entity.Room;
import com.example.dbdesign.model.request.RoomAddRequest;
import com.example.dbdesign.model.request.RoomSearchByRoomNumRequest;
import com.example.dbdesign.model.request.RoomUpdateRequest;
import com.example.dbdesign.service.RoomService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zzs
 * @date 2023/5/22 14:52
 */
@RestController
@RequestMapping("/bill")
public class RoomController {

    @Resource
    private RoomService roomService;

    /**
     * 管理员添加房间接口
     *
     * @param roomAddRequest 房间添加请求
     * @return 是否添加成功
     */
    @PostMapping("/addRoom")
    public BaseResponse<Boolean> addRoom(@RequestBody RoomAddRequest roomAddRequest) {
        if (BeanUtil.isEmpty(roomAddRequest)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Boolean addRoom = roomService.addRoom(roomAddRequest);
        if (Boolean.FALSE.equals(addRoom)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "添加房间失败");
        }
        return ResultUtils.success(true, "添加房间成功");
    }
    @PostMapping("/UpdateRoom")
    public BaseResponse<Boolean> UpdateRoom(@RequestBody RoomUpdateRequest roomUpdateRequest){
        if(BeanUtil.isEmpty(roomUpdateRequest)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Boolean UpdateRoom = roomService.UpdateRoom(roomUpdateRequest);
        if(Boolean.FALSE.equals(UpdateRoom)){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"更新房间失败");
        }
        return ResultUtils.success(true,"更新房间成功");
    }

    @PostMapping("SearchRoomByName")
    public BaseResponse<Room> queryRoomByRoomNum(@RequestBody RoomSearchByRoomNumRequest roomSearchByRoomNumRequest){
        if(BeanUtil.isEmpty(roomSearchByRoomNumRequest)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Room SearchRoom = roomService.queryRoomByRoomNum(roomSearchByRoomNumRequest);
        return ResultUtils.success(SearchRoom,"查询房间成功");
    }

    @GetMapping("/getRooms")
    public BaseResponse<List<Room>> getRooms() {
        List<Room> roomList = roomService.getAllRooms();
        return ResultUtils.success(roomList, "获取房间列表成功");
    }

}
