package com.example.dbdesign.controller;

import com.example.dbdesign.service.UserItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/UseItem")
public class UseItemController {
    @Resource
    private UserItemService userItemService;


}
