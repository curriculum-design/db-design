package com.example.dbdesign.mapper;

import com.example.dbdesign.model.entity.Bill;
import com.example.dbdesign.model.request.SaveBillRequest;
import com.example.dbdesign.model.request.OutBillRequest;
import org.apache.ibatis.annotations.Param;


public interface BillMapper {
    /**
     * 加入账单，向bill表中插入一条数据
     * @param SaveBillRequest 加入账单请求
     * @return 受影响行数
     */
    Integer saveBill(SaveBillRequest SaveBillRequest);

    /**
     *结账，向bill表中删除一条数据
     * @param outBillRequest 加入账单请求
     * @return 受影响行数
     */

    Integer outBill(OutBillRequest outBillRequest);

    /**
     * 根据账单id来查询账单信息
     * @param id 账单id
     * @return 账单信息
     */
    Bill queryBillByid(@Param("id") Long id);
}
