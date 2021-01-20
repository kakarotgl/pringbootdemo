package com.example.demo.service;
import com.example.demo.bean.ImgEntity;

public interface MysqlBlobService {
    Integer saveBlobImg(ImgEntity img);
    byte[] showPicture(String id);
    public String getFileName(String id);
}
