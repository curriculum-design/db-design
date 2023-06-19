package com.example.dbdesign.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.dbdesign.common.BaseResponse;
import com.example.dbdesign.common.ErrorCode;
import com.example.dbdesign.common.ResultUtils;
import com.example.dbdesign.exception.BusinessException;
import com.example.dbdesign.model.dto.UserDTO;
import com.example.dbdesign.model.entity.Bill;
import com.example.dbdesign.model.entity.ItemConsumeInfo;
import com.example.dbdesign.model.entity.RoomConsumeInfo;
import com.example.dbdesign.model.request.CalculateRequest;
import com.example.dbdesign.model.entity.Room;
import com.example.dbdesign.model.request.OutBillRequest;
import com.example.dbdesign.model.request.QueryBillRequest;
import com.example.dbdesign.model.request.SaveBillRequest;
import com.example.dbdesign.service.BillService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import static com.example.dbdesign.controller.UserController.SESSION_KEY;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Resource
    private BillService billService;

    @PostMapping("/saveBill")
    public BaseResponse<Boolean> saveBill(@RequestBody SaveBillRequest saveBillRequest){
        if(BeanUtil.isEmpty(saveBillRequest)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数错误");
        }
        Boolean saveBill = billService.saveBill(saveBillRequest);
        if(Boolean.FALSE.equals(saveBill)){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"保存账单失败");
        }
        return ResultUtils.success(true,"保存订单成功");
    }

    @PostMapping("outBill")
    public BaseResponse<Boolean> OutBill(@RequestBody OutBillRequest outBillRequest){
        if(BeanUtil.isEmpty(outBillRequest)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Boolean outBill = billService.outBill(outBillRequest);
        if(Boolean.FALSE.equals(outBill)){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"结账失败");
        }
        return ResultUtils.success(true,"结账成功");
    }
    @PostMapping("queryBill")
    public BaseResponse<Bill> queryBillByid(@RequestBody QueryBillRequest queryBillRequest){
        if(BeanUtil.isEmpty(queryBillRequest)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Bill queryBill = billService.queryBillByid(queryBillRequest);
        return ResultUtils.success(queryBill,"查找成功");
    }
    @GetMapping("/getBills")
    public BaseResponse<List<Bill>> getBills() {
        List<Bill> billList = billService.getAllBills();
        return ResultUtils.success(billList, "获取账单列表成功");
    }

    @PostMapping("/consumeItem")
    public BaseResponse<Integer> CalculatePrice(@RequestBody CalculateRequest calculateRequest, HttpServletRequest request){
        if(BeanUtil.isEmpty(calculateRequest)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserDTO loginUser = (UserDTO) request.getSession().getAttribute(SESSION_KEY);
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        Integer FinalPrice = billService.calculatePrice(calculateRequest, loginUser.getId());
        return ResultUtils.success(FinalPrice,"总金额计算完成");
    }

    @PostMapping("/getItemConsumeInfo")
    public BaseResponse<List<ItemConsumeInfo>> getItemConsumeInfo(@RequestBody CalculateRequest calculateRequest, HttpServletRequest request) {
        if (BeanUtil.isEmpty(calculateRequest)) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        List<ItemConsumeInfo> itemConsumeInfo = billService.getItemConsumeInfo(calculateRequest);
        return ResultUtils.success(itemConsumeInfo, "获取物品消费信息成功");
    }

    @GetMapping("/getRoomConsumeInfo")
    public BaseResponse<RoomConsumeInfo> getRoomConsumeInfo(Long roomId, HttpServletRequest request) {
        if (roomId == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        UserDTO loginUser = (UserDTO) request.getSession().getAttribute(SESSION_KEY);
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        RoomConsumeInfo roomConsumeInfo = billService.getRoomConsumeInfo(roomId, loginUser.getId());
        return ResultUtils.success(roomConsumeInfo, "获取房间费用信息成功");
    }
}
