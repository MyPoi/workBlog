package com.yomo.blog.controller;

import cn.hutool.core.util.StrUtil;
import com.yomo.blog.utils.QiniuUtils;
import com.yomo.blog.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
import static com.yomo.blog.utils.ErrorCode.*;

/**
 * <p>
 * TODO:请用一句话描述
 * </p>
 *
 * @author yomo
 * @since 2022/4/21
 */
@RestController
@RequestMapping("upload")
public class UploadController {
    
    @Autowired
    private QiniuUtils qiniuUtils;
    
    @PostMapping
    public Result upload(@RequestParam("image")MultipartFile file){
        // 原始文件名称 比如 a.png
        String originalFilename = file.getOriginalFilename();
        // 唯一的文件名称
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");
        // 上传文件 七牛云 云服务器 按量收费 速度快 把图片发送到离用户最近的服务器上
        // 降低我们自身的服务器带宽消耗
        boolean upload = qiniuUtils.upload(file, fileName);
        if (upload) return Result.success(qiniuUtils.url + fileName);
        return Result.failure(UPDATE_ERROR.getCode(), UPDATE_ERROR.getMsg());
    }
    
}
