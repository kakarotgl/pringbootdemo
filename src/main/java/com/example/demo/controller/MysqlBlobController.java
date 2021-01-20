package com.example.demo.controller;

import com.example.demo.bean.ImgEntity;
import com.example.demo.model.ResponseJson;
import com.example.demo.service.MysqlBlobService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@ResponseBody
@RequestMapping("/demo")
public class MysqlBlobController {
    @Resource
    MysqlBlobService mysqlBlobService;
    private static String  uploadPath =  "E:/content/imgs/";

    @RequestMapping("/saveBlobImg")
    public ResponseJson<Integer> getBlog(@RequestParam("uploadFile") MultipartFile uploadFile) throws IOException {
        ImgEntity imgEntity = new ImgEntity();
        String oldName = uploadFile.getOriginalFilename();
        String fileId = UUID.randomUUID().toString();

        imgEntity.setFilename(oldName);
        imgEntity.setId(fileId);
        System.out.println(fileId);
        imgEntity.setContent(uploadFile.getBytes());
        int rs = mysqlBlobService.saveBlobImg(imgEntity);
        if(rs>0)
            return new ResponseJson<Integer>().success().data(rs);
        else
            return new ResponseJson<Integer>().fail().data(rs);
    }

    /**
     * 图片回显
     */
    @RequestMapping(value="/showPicture")
    public void showPicture(String id, HttpServletResponse response) throws Exception{
        //设置头部(图片)
        //response.setContentType("image/jpeg");
        //tet.doc为文件名
        String filename = mysqlBlobService.getFileName(id);
        response.addHeader("Content-Disposition", "attachment;filename="+new String(filename.getBytes()));
        //根据Id获取数据
        byte[] img = mysqlBlobService.showPicture(id);
        response.getOutputStream().write(img);
        response.flushBuffer();
    }
}
