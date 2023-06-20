package com.example.dbdesign.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.example.dbdesign.common.ErrorCode;
import com.example.dbdesign.exception.BusinessException;
import com.example.dbdesign.mapper.BillMapper;
import com.example.dbdesign.model.entity.*;
import com.example.dbdesign.model.request.CalculateRequest;
import com.example.dbdesign.model.request.OutBillRequest;
import com.example.dbdesign.model.request.QueryBillRequest;
import com.example.dbdesign.model.request.SaveBillRequest;
import com.example.dbdesign.service.BillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Resource
    private BillMapper billMapper;

    public Boolean saveBill(SaveBillRequest saveBillRequest){
        if(BeanUtil.hasNullField(saveBillRequest)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Integer billPrice = saveBillRequest.getPrice();
        if(billPrice < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"订单价格出错");
        }
        return billMapper.saveBill(saveBillRequest)>0;
    }

    public Boolean outBill(OutBillRequest outBillRequest){
        if(BeanUtil.hasNullField(outBillRequest)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Integer isPayed = outBillRequest.getIsPay();
        if(isPayed !=0 && isPayed != 1 ){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"结账参数为0/1");
        }
        return billMapper.outBill(outBillRequest)>0;
    }

    @Override
    public Bill queryBillByid(QueryBillRequest queryBillRequest) {
        if(BeanUtil.hasNullField(queryBillRequest)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long Id = queryBillRequest.getId();
        Bill bill = billMapper.queryBillByid(Id);
        return bill;
    }

    @Override
    public List<Bill> getAllBills() {
        return billMapper.queryBills();
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer calculatePrice(CalculateRequest calculateRequest, Long userId){
        if(BeanUtil.hasNullField(calculateRequest)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Integer totalPrice = billMapper.calculatePrice(calculateRequest.getRoomId(), calculateRequest.getConsumeList(), userId);
        List<ItemConsume> consumeList = calculateRequest.getConsumeList();
        for (ItemConsume itemConsume : consumeList) {
            Integer itemSell = billMapper.updateItemSell(itemConsume);
            if (itemSell <= 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "更新库存失败");
            }
        }
        if (totalPrice <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //向订单表插入数据
        Long roomId = calculateRequest.getRoomId();
        if (roomId == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR, "房间id不能为空");
        }
        SaveBillRequest saveBillRequest = new SaveBillRequest();
        saveBillRequest.setRoomId(roomId);
        saveBillRequest.setUserId(userId);
        saveBillRequest.setPrice(totalPrice);
        saveBillRequest.setIsPay(0);
        Integer saveBill = billMapper.saveBill(saveBillRequest);
        if (saveBill <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        return totalPrice;
    }

    @Override
    public List<ItemConsumeInfo> getItemConsumeInfo(CalculateRequest calculateRequest) {
        if (BeanUtil.hasNullField(calculateRequest)) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        List<ItemConsumeInfo> itemConsumeList = billMapper.getItemConsumeList(calculateRequest);
        if (itemConsumeList.isEmpty()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return itemConsumeList;
    }

    @Override
    public RoomConsumeInfo getRoomConsumeInfo(Long roomId, Long userId) {
        if (roomId == null || userId == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        RoomConsumeInfo roomConsumeInfo = billMapper.getRoomConsumeInfo(roomId, userId);
        if (roomConsumeInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return roomConsumeInfo;
    }
}