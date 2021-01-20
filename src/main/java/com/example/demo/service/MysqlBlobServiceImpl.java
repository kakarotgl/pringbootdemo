package com.example.demo.service;

import com.example.demo.bean.ImgEntity;
import com.example.demo.mapper.MysqlBlobMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class MysqlBlobServiceImpl implements MysqlBlobService{
    @Resource
    private MysqlBlobMapper mysqlBlobMapper;

    @Override
    public Integer saveBlobImg(ImgEntity img) {
        return mysqlBlobMapper.saveBlobImg(img);
    }

    @Override
    public byte[] showPicture(String id) {
        //调用mapper
        Map map = mysqlBlobMapper.showPicture(id);
        //如果返回数据包含'content'(数据库字段)且数据不为空
        if (map != null && map.containsKey("content")){
            //获取value且转为byte[]返回
            return (byte[]) map.get("content");
        }else {
            return null;
        }
    }

    @Override
    public String getFileName(String id) {
        return mysqlBlobMapper.getFileName(id);
    }


}
