package com.example.dbdesign.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 新增账单请求类
 * @author 姜楠
 * @date 2023/6/11 11:29
 */
@Data
public class SaveBillRequest implements Serializable{

    /**
     * 房间id
     */
    private long roomId;

    /**
     * 用户id
     */
    private long userId;

    /**
     * 价格:总共
     */
    private Integer price;

    //private List<SailedItem> sailedItemList;

    /**
     * 状态（0-未支付，1-已支付）
     */
    private Integer isPay;
}
