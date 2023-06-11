package com.example.dbdesign.service;



import com.example.dbdesign.model.entity.Bill;
import com.example.dbdesign.model.request.queryBillRequest;
import com.example.dbdesign.model.request.SaveBillRequest;
import com.example.dbdesign.model.request.OutBillRequest;
public interface BillService {
    /**
     * 添加账单
     * @param SaveBillRequest 账单添加请求
     * @return 是否添加成功
     */

    //Boolean saveBill(SaveBillRequest SaveBillRequest);

    /**
     * 结账也就是删除账单
     * @param OutBillRequest 账单添加请求
     * @return 是否添加成功
     */
    //Boolean outBill(OutBillRequest OutBillRequest);


    /**
     * 通过账单ID进行查询账单信息
     * @param
     * @return
     */
    //Bill queryBillByid(queryBillRequest queryBillRequest);
}
