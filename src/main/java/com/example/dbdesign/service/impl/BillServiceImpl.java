package com.example.dbdesign.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.example.dbdesign.common.ErrorCode;
import com.example.dbdesign.exception.BusinessException;
import com.example.dbdesign.mapper.BillMapper;
import com.example.dbdesign.model.entity.Bill;
import com.example.dbdesign.model.entity.Room;
import com.example.dbdesign.model.request.OutBillRequest;
import com.example.dbdesign.model.request.QueryBillRequest;
import com.example.dbdesign.model.request.SaveBillRequest;
import com.example.dbdesign.service.BillService;
import org.springframework.stereotype.Service;

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
}