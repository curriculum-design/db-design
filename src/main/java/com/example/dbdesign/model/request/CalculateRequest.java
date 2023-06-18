package com.example.dbdesign.model.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CalculateRequest implements Serializable {
    List<Long> itemId;



}
