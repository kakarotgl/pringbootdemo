package com.example.demo.mapper;

import com.example.demo.bean.ImgEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

public interface MysqlBlobMapper {
    Integer saveBlobImg(ImgEntity img);

    Map<String, Object> showPicture(String id);

    String getFileName(String id);
}
