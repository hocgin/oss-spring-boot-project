package in.hocg.alioss.spring.boot.autoconfigure.impl;

import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import in.hocg.alioss.spring.boot.autoconfigure.core.OssFileService;
import in.hocg.alioss.spring.boot.autoconfigure.exception.UploadOssException;
import in.hocg.alioss.spring.boot.autoconfigure.properties.OssProperties;
import in.hocg.alioss.spring.boot.autoconfigure.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * Created by hocgin on 2020/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AliOssFileService implements OssFileService {
    private final OssProperties properties;

    @Override
    public String upload(MultipartFile file) {
        try {
            File uploadFile = FileUtils.createTempFile(file.getOriginalFilename()).toFile();
            file.transferTo(uploadFile);
            return this.upload(uploadFile);
        } catch (IOException e) {
            log.warn("读取上传文件失败", e);
            throw new UploadOssException("文件上传失败");
        }
    }

    @Override
    public String upload(File file) {
        return this.upload(file, getFileName(file));
    }

    @Override
    public String upload(File file, String filename) {
        String bucket = properties.getBucket();
        return this.upload(file, filename, bucket);
    }

    @Override
    public String upload(File file, String filename, String space) {
        try {
            return this.upload(Files.newInputStream(file.toPath()), filename, space);
        } catch (IOException e) {
            log.warn("读取上传文件失败", e);
            throw new UploadOssException("文件上传失败");
        }
    }

    @Override
    public String upload(InputStream is, String filename, String space) {
        UploadManager uploadManager = new UploadManager(new Configuration(Region.region0()));
        String uploadToken = getUploadToken(space);
        String key;
        try {
            Response response = uploadManager.put(is, filename, uploadToken, null, null);
            JSONObject result = JSONUtil.parseObj(response.bodyString());
            key = result.getStr("key");
        } catch (QiniuException e) {
            log.error("上传文件到七牛失败, 响应内容为:[{}]", e.response, e);
            throw new UploadOssException("文件上传失败");
        }
        return getFileUrl(key);
    }

    private String getFileUrl(String key) {
        String domain = properties.getDomain();
        return URLUtil.completeUrl(domain, key);
    }

    private String getUploadToken(String bucket) {
        String accessKey = properties.getAccessKey();
        String secretKey = properties.getSecretKey();
        return Auth.create(accessKey, secretKey).uploadToken(bucket);
    }
}
