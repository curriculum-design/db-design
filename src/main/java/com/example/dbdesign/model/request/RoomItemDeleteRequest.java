package com.example.dbdesign.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoomItemDeleteRequest implements Serializable {
    private Integer is_delete;

    private long id;
}