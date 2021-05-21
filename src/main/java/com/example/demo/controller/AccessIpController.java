package com.example.demo.controller;

import com.example.demo.utils.AccessIpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/demo")
public class AccessIpController {
    @RequestMapping("ip")
    public String getIp(HttpServletRequest request){
        return AccessIpUtil.getIp(request);
    }

}
