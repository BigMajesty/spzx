package com.spzx.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.spzx.common.exception.GlobalException;
import com.spzx.manager.properties.MinioProperties;
import com.spzx.manager.service.FileUploadService;
import com.spzx.model.vo.common.ResultCodeEnum;
import io.minio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

/**
 * ClassName: FileUploadServiceImpl
 * Package: com.spzx.manager.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/13 - 16:07
 * Version: v1.0
 */

@Service
public class FileUploadServiceImpl implements FileUploadService {
    //minio属性读取
    @Autowired
    MinioProperties minioProperties;

    @Override
    public String upload(MultipartFile file) {
        try {
            //创建minio client对象
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(minioProperties.getEndpointUrl())
                            .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                            .build();

            //创建 bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
            if (!found) {
                // Make a new bucket called spzx-bucket
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());
            } else {
                System.out.println("Bucket already exists.");
            }

            // 文件上传
            //获取上传文件名称:20240413/uuid+name
            String dateDir = DateUtil.format(new Date(), "yyyyMMdd");
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            String fileName = dateDir+"/"+uuid+file.getOriginalFilename();
            // Upload known sized input stream.
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(minioProperties.getBucketName())
                                    .object(fileName)
                                    .stream(file.getInputStream(), file.getSize(), -1)
                                    .build());
            //获取上传文件在minio的路径
            //http://127.0.0.1:9000/spzx-bucket/1.png
            String url = minioProperties.getEndpointUrl()+"/"+minioProperties.getBucketName()+"/"+fileName;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(ResultCodeEnum.SYSTEM_ERROR);
        }
    }
}
