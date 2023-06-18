package com.example.dbdesign.model.request;

import com.example.dbdesign.model.entity.ItemConsume;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CalculateRequest implements Serializable {

    private Long roomId;
    private List<ItemConsume> consumeList;
}
