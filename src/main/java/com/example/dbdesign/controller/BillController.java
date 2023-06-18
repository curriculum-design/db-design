package com.example.dbdesign.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.dbdesign.common.BaseResponse;
import com.example.dbdesign.common.ErrorCode;
import com.example.dbdesign.common.ResultUtils;
import com.example.dbdesign.exception.BusinessException;
import com.example.dbdesign.model.entity.Bill;
import com.example.dbdesign.model.entity.Room;
import com.example.dbdesign.model.request.OutBillRequest;
import com.example.dbdesign.model.request.QueryBillRequest;
import com.example.dbdesign.model.request.SaveBillRequest;
import com.example.dbdesign.service.BillService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
}
