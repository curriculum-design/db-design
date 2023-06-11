package com.example.dbdesign.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class OutUserItemRequest implements Serializable {
    /**
     * 用户id
     */
    private long userId;
}
