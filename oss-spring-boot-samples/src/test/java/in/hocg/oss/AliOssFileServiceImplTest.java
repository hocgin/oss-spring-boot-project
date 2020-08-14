package in.hocg.oss;

import in.hocg.oss.core.AbstractSpringBootTest;
import in.hocg.oss.spring.boot.autoconfigure.core.OssFileService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;

/**
 * Created by hocgin on 2020/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@ActiveProfiles("aliyun")
public class AliOssFileServiceImplTest extends AbstractSpringBootTest {
    @Autowired
    OssFileService ossFileService;

    @Test
    public void testUpload() {
        File file = new File("/Users/hocgin/Downloads/1111.png");
        String url = ossFileService.upload(file);
        log.info("==> {}", url);
    }
}
