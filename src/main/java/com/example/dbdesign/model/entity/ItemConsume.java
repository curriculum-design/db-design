package com.example.dbdesign.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zzs
 * @date 2023/6/18 15:53
 */
@Data
public class ItemConsume implements Serializable {

    private Long itemId;

    private Integer count;
}
