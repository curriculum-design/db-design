package com.example.dbdesign.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SailedItem implements Serializable {
    private String itemName;

    private String num;

    private Integer price;
}
