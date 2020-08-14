package com.klxx.edu.common.oss.core;

import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

/**
 * Created by hocgin on 2020/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface OssFileService {

    String upload(MultipartFile file);

    String upload(File file);

    String upload(File file, String filename);

    String upload(File file, String filename, String space);

    String upload(InputStream is, String filename, String space);

    default String getFileName(File file) {
        return DigestUtil.md5Hex(file) + "/" + file.getName();
    }
}
