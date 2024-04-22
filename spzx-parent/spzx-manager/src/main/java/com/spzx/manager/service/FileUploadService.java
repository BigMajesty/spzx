package com.spzx.manager.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: FileUploadService
 * Package: com.spzx.manager.service
 * Description:
 * Author: zhj
 * Create: 2024/4/13 - 16:07
 * Version: v1.0
 */
public interface FileUploadService {
    String upload(MultipartFile file);
}
