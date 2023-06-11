package com.example.dbdesign.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class OutBillRequest implements Serializable {

    private Integer isPay;
    private long id;

    private Integer roomId;

    private Integer userId;
}
