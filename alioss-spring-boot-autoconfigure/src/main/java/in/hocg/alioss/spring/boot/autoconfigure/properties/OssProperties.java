package in.hocg.alioss.spring.boot.autoconfigure.properties;


import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by hocgin on 2019/6/12.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ToString
@ConfigurationProperties(prefix = AliOssProperties.PREFIX)
public class AliOssProperties {
    public static final String PREFIX = "aliyun.oss";
    private String accessKey;
    private String secretAccess;
    private String endpoint;
    private String domain;
}
