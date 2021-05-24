package com.example.demo.service.impl;

import com.example.demo.service.FileService;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class FileServiceMinioImpl implements FileService {

    private MinioClient minioClient;
    @Value("${minio.endpoint}")
    private String minioEndpoint;
    @Value("${minio.accesskey}")
    private String minioAccessKey;
    @Value("${minio.secretKey}")
    private String minioSecretKey;


    @Override
    public InputStream read(String bucket, String fileId, String path) {
        return this.download(bucket,fileId);
    }

    @Override
    public void write(String content, String bucket, String fileId, String path, String fileType) {
        this.upload(bucket,fileId,content,fileType);
    }

    /**
     * @Description  ：上传
     * @author       : wh
     * @param        : [bucket, file]
     * @return       : void
     * @date         : 2021/5/6 11:18
     */
    public void upload(String bucket, MultipartFile file){
        String fileName = file.getOriginalFilename();
        String fileId = UUID.randomUUID().toString()+fileName;
        MinioClient minioClient = this.getMinioClient();
        try {
            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(bucket).build());
            if (isExist) {
                System.out.println(String.format("Bucket[%s] already exists.", bucket));
            } else {
                // 创建一个名为erpdev的存储桶
                minioClient.makeBucket(
                        MakeBucketArgs.builder()
                                .bucket(bucket)
                                .build());
            }
            //使用Inpustream上传
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucket).object(fileId).stream(
                            file.getInputStream(), -1, 10485760)
                            .object(fileId)
                            .contentType(file.getContentType())
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void upload(String bucket, String fileId, String file, String fileType){
        InputStream stream = null;
        try {
            stream = new ByteArrayInputStream(file.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("字符串转流出错");
        }
        MinioClient minioClient = this.getMinioClient();
        try {
            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(bucket).build());
            if (isExist) {
                System.out.println(String.format("Bucket[%s] already exists.", bucket));
            } else {
                // 创建一个名为erpdev的存储桶
                minioClient.makeBucket(
                        MakeBucketArgs.builder()
                                .bucket(bucket)
                                .build());
            }
            //使用Inpustream上传
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucket).object(fileId).stream(
                            stream, -1, 10485760)
                            .object(fileId)
                            .contentType(fileType)
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDownloadUrl(String bucket, String fileId, FileType fileType) {
        MinioClient minioClient = this.getMinioClient();
        // Get presigned URL string to download 'my-objectname' in 'my-bucketname' and its life time is 2 hours.
        String url = null;
        try {
            url = minioClient.getPresignedObjectUrl(
                            GetPresignedObjectUrlArgs.builder()
                                    .method(Method.GET)
                                    .bucket(bucket)
                                    .object(fileId)
                                    .expiry(2, TimeUnit.HOURS)
                                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("生成下载url出错:"+e.getMessage());
        }
        return url;
    }

    /**
     * @Description  ：下载 TODO
     * @author       : wh
     * @param        : [bucket]
     * @return       : void
     * @date         : 2021/5/6 11:18
     */
    public InputStream download(String bucket,String fileId){
        MinioClient minioClient = this.getMinioClient();
        InputStream stream = null;
        try {
            stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .object(fileId)
                            .bucket(bucket)
                            .build());
//            StringBuilder sb = new StringBuilder();
//            String line;
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
//            while ((line = bufferedReader.readLine()) != null) {
//                sb.append(line);
//                sb.append(System.lineSeparator());
//            }
//            System.out.println(sb.toString());
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("下载文件失败");
        }
        return stream;
    }

    private MinioClient getMinioClient(){
        if(this.minioClient == null){
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(minioEndpoint)
                            .credentials(minioAccessKey, minioSecretKey)
                            .build();
            this.minioClient = minioClient;
        }
        return this.minioClient;
    }
}
