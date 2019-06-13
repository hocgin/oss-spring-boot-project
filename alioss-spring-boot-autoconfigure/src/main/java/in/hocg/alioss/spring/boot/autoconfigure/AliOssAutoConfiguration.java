package in.hocg.alioss.spring.boot.autoconfigure;

import com.aliyun.oss.OSSClient;
import in.hocg.alioss.spring.boot.autoconfigure.properties.AliOssProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

/**
 * Created by hocgin on 2019/6/12.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Log
@Configuration
@ConditionalOnProperty(prefix = AliOssProperties.PREFIX, name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(AliOssProperties.class)
@RequiredArgsConstructor
public class AliOssAutoConfiguration {
    private final AliOssProperties properties;
    
    @Bean
    @ConditionalOnMissingBean
    public OSSClient ossClient() {
        String endpoint = properties.getEndpoint();
        String accessKey = properties.getAccessKey();
        String secretAccess = properties.getSecretAccess();
        
        Assert.notNull(endpoint, String.format("%s.endpoint 为必填项", AliOssProperties.PREFIX));
        Assert.notNull(accessKey, String.format("%s.accessKey 为必填项", AliOssProperties.PREFIX));
        Assert.notNull(secretAccess, String.format("%s.secretAccess 为必填项", AliOssProperties.PREFIX));
        
        return new OSSClient(endpoint, accessKey, secretAccess);
    }
}
