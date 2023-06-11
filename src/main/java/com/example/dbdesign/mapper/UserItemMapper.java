package com.example.dbdesign.mapper;
import com.example.dbdesign.model.entity.UserItem;
import com.example.dbdesign.model.request.SaveUserItemRequest;
import com.example.dbdesign.model.request.OutUserItemRequest;
public interface UserItemMapper {
    Integer saveUserItem(SaveUserItemRequest saveUserItemRequest);

    Integer outUserItem(OutUserItemRequest outUserItemRequest);
}
