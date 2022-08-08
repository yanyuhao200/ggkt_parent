package com.atguigu.ggkt.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author yyh
 * Date:  2022/08/05 17:54
 */

public interface FileService {
    //文件上传
    String upload(MultipartFile file);
}
