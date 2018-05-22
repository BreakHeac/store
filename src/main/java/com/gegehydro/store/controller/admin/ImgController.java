package com.gegehydro.store.controller.admin;

import com.alibaba.fastjson.JSON;
import com.gegehydro.store.util.BaseResp;
import com.gegehydro.store.util.BucketUpload;
import com.gegehydro.store.util.ResultStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 图片控制类
 *
 * @author sunhao
 * @date 2018/2/11
 */
@CrossOrigin
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(value = "/img", produces = "application/json;charset=UTF-8")
public class ImgController {

    @PostMapping(value = "/uploadImg")
    public String uploadImg(MultipartFile file, int appId) {
        if (0 >= appId) {
            return JSON.toJSONString(new BaseResp<>(ResultStatus.EXIST_ERROR));
        }
        try {
            File f = null;
            f = File.createTempFile("tmp", null);
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            file.transferTo(f);
            f.deleteOnExit();
            String url = BucketUpload.upload(f, appId, suffix);
            String big = "big";
            if (big.equalsIgnoreCase(url)) {
                return JSON.toJSONString(new BaseResp<>(ResultStatus.LARGE));
            }
            return JSON.toJSONString(new BaseResp<>(ResultStatus.SUCCESS, url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(new BaseResp<>(ResultStatus.FAIL));
    }
}
