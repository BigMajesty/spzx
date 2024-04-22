package com.spzx.manager.controller;

import com.spzx.manager.service.FileUploadService;
import com.spzx.model.vo.common.Result;
import com.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: FileUploadController
 * Package: com.spzx.manager.controller
 * Description:
 * Author: zhj
 * Create: 2024/4/13 - 16:06
 * Version: v1.0
 */
@RestController
@RequestMapping("/admin/system")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 获取存储值minio的图片路径
     * @param file
     * @return
     */
    @PostMapping("/fileUpload")         //file的名字固定，不能乱改
    public Result fileUpload(MultipartFile file){
        //获取上传的文件
        //调用service方法上传，返回minio路径
        String url = fileUploadService.upload(file);
        return Result.build(url, ResultCodeEnum.SUCCESS);
    }
}
