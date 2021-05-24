package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileService {

    /**
     * @Description  ：读取文件
     * @author       : wh
     * @param        : [fileId, path]
     * @return       : java.io.InputStream
     * @date         : 2021/5/10 9:24
     */
    InputStream read(String bucket, String fileId, String path);

    /**
     * @Description  ：写文件
     * @author       : wh
     * @param        : [outputStream]
     * @return       : void
     * @date         : 2021/5/10 9:25
     */
    void write(String content, String bucket, String fileId, String path, String fileType);

    /**
     * @Description  ：上传文件
     * @author       : wh
     * @param        : [bucket, fileId, file]
     * @return       : void
     * @date         : 2021/5/10 10:19
     */
    void upload(String bucket, MultipartFile file);

    /**
     * @Description  ：获取下载url
     * @author       : wh
     * @param        : [bucket, fileId]
     * @return       : java.lang.String
     * @date         : 2021/5/18 11:06
     */
    String getDownloadUrl(String bucket, String fileId, FileType fileType);


    enum FileType {
        FeedReport("FeedReport"),
        ;
        private String value;
        FileType(String v){
            this.value = v;
        }
    }
}
