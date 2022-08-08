package com.atguigu.ggkt.vod.controller;

import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vod.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yyh
 * Date:  2022/08/05 17:51
 */

@Api(tags = "文件上传接口")
@RestController
@RequestMapping("/admin/vod/file")
@CrossOrigin
public class FileUploadController {

    private final FileService fileService;

    public FileUploadController(FileService fileService) {
        this.fileService = fileService;
    }

    @ApiOperation("文件上传")
    @PostMapping("upload")
    // 上传文件 需要得到上传文件 使用 SpringMVC里面封装的MultipartFile可以得到
    public Result uploadFile(MultipartFile file){
        // 此文件为腾讯云里面的路径 String
        String url = fileService.upload(file);
        return Result.ok(url).message("上传文件成功");
    }
}
