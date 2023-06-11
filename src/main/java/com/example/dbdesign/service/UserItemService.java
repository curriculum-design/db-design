package com.example.dbdesign.service;

import com.example.dbdesign.model.request.SaveUserItemRequest;

public interface UserItemService {

    /**
     * 用户使用物品接口
     *
     * @param saveUserItem 用户添加使用物品请求
     * @return 是否添加成功
     */
    Boolean saveUserItem(SaveUserItemRequest saveUserItemRequest);

}
