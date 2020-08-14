## 介绍
> 集成`七牛OSS`/`阿里OSS`

## 使用
#### Maven
```
    <!--...-->
    <dependencies>
        <dependency>
            <groupId>in.hocg.oss</groupId>
            <artifactId>oss-spring-boot-starter</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
    </dependencies>

    <repositories>
        <repository>
            <id>public</id>
            <name>public</name>
            <url>https://oss.sonatype.org/content/groups/public/</url>
        </repository>
    </repositories>
```
#### application.yml
```yaml
oss:
  enabled: true
  domain: http://endpoint.com
  secretKey: <secret-access/>
  accessKey: <access-key/>
  space: <default-space/>
  type: <aliyun|qiniu/>
```
#### Java
```java
@RestController
@RequestMapping
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class IndexController {
    private final OssFileService ossFileService;

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(ossFileService.upload(file));
    }

}
```

## 项目结构
```sbtshell
.
├── README.md
├── pom.xml // 项目信息
├── oss-spring-boot-autoconfigure // 启动器
├── oss-spring-boot-parent  // 依赖
└── oss-spring-boot-starter  // Starter
```
