package com.skyblue.rabbitmusic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("default")
public class DefaultController {
    private static final Logger log = LoggerFactory.getLogger(DefaultController.class);

    @RequestMapping("")
    public String test() {
        log.info("访问了sayhello");
        return "say hello";
    }


}
