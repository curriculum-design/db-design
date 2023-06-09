package com.example.dbdesign.model.request;

import lombok.Data;

@Data
public class RoomItemDeleteRequest {
    private Integer is_delete;

    private long id;
}
