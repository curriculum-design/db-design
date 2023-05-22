package com.example.dbdesign.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.dbdesign.common.BaseResponse;
import com.example.dbdesign.common.ErrorCode;
import com.example.dbdesign.common.ResultUtils;
import com.example.dbdesign.exception.BusinessException;
import com.example.dbdesign.model.dto.UserDTO;
import com.example.dbdesign.model.request.UserOpenRoomRequest;
import com.example.dbdesign.service.UserRoomService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.example.dbdesign.constants.UserConstant.SESSION_KEY;

/**
 * @author zzs
 * @date 2023/5/22 15:45
 */
@RestController
@RequestMapping("/userRoom")
public class UserRoomController {

    @Resource
    private UserRoomService userRoomService;

    /**
     * 用户开房接口
     * @param userOpenRoomRequest 用户开房请求
     * @param request servlet请求
     * @return 是否开房成功
     */
    @PostMapping("/openRoom")
    public BaseResponse<Boolean> userOpenRoom(@RequestBody UserOpenRoomRequest userOpenRoomRequest, HttpServletRequest request) {
        if (BeanUtil.isEmpty(userOpenRoomRequest)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserDTO loginUser = (UserDTO) request.getSession().getAttribute(SESSION_KEY);
        if (BeanUtil.isEmpty(loginUser)) {
            throw new BusinessException(ErrorCode.NOT_LOGIN, "用户未登录");
        }
        Long userId = loginUser.getId();
        if (!userOpenRoomRequest.getUserId().equals(userId)) {
            throw new BusinessException(ErrorCode.NOT_LOGIN, "用户id参数与发起请求的用户id不一致");
        }
        Boolean openRoom = userRoomService.userOpenRoom(userOpenRoomRequest);
        if (Boolean.FALSE.equals(openRoom)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "开房失败");
        }
        return ResultUtils.success(true, "开房成功");
    }

}
