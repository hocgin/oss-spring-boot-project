package in.hocg.alioss.spring.boot.samples;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hocgin on 2019/6/13.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Controller
@RequestMapping
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class IndexController {
    
    private final OSSClient ossClient;
    
    @PostMapping("upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        // 文件名: file.getOriginalFilename();
        String filename = "tFile";
        PutObjectResult result = ossClient.putObject("Test", filename, file.getInputStream());
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("download")
    public ResponseEntity download() {
        String filename = "tFile";
        OSSObject fObj = ossClient.getObject("Test", filename);
        String fName = fObj.getKey();
        long size = fObj.getObjectMetadata().getContentLength();
        InputStream fStream = fObj.getObjectContent();
        return ResponseEntity
                .ok()
                .headers(new HttpHeaders() {{
                    add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
                    add(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", fName));
                    add(HttpHeaders.PRAGMA, "no-cache");
                    add(HttpHeaders.EXPIRES, "0");
                }})
                .contentLength(size)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(fStream));
    }
}
