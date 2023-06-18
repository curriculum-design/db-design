package com.example.dbdesign.service;



import com.example.dbdesign.model.entity.Bill;
import com.example.dbdesign.model.request.CalculateRequest;
import com.example.dbdesign.model.request.QueryBillRequest;
import com.example.dbdesign.model.request.SaveBillRequest;
import com.example.dbdesign.model.request.OutBillRequest;
import io.swagger.models.auth.In;

public interface BillService {
    /**
     * 添加账单
     * @param saveBillRequest 账单添加请求
     * @return 是否添加成功
     */

    Boolean saveBill(SaveBillRequest saveBillRequest);

    /**
     * 结账也就是删除账单
     * @param outBillRequest 账单添加请求
     * @return 是否添加成功
     */
    Boolean outBill(OutBillRequest outBillRequest);


    /**
     * 通过账单ID进行查询账单信息
     */
    Bill queryBillByid(QueryBillRequest queryBillRequest);

    Integer CalculatePrice(CalculateRequest calculateRequest);
}
