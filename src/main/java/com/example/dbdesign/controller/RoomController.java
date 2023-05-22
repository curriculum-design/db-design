package com.example.dbdesign.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.dbdesign.common.BaseResponse;
import com.example.dbdesign.common.ErrorCode;
import com.example.dbdesign.common.ResultUtils;
import com.example.dbdesign.exception.BusinessException;
import com.example.dbdesign.model.request.RoomAddRequest;
import com.example.dbdesign.service.RoomService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zzs
 * @date 2023/5/22 14:52
 */
@RestController
@RequestMapping("/room")
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

}
