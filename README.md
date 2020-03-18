```sbtshell
.
├── README.md
├── pom.xml // 项目信息
├── alioss-spring-boot-autoconfigure // 启动器
├── alioss-spring-boot-parent  // 依赖
└── alioss-spring-boot-starter  // Starter
```


## 使用
### application.yml
```sbtshell
aliyun:
  oss:
    endpoint: http://endpoint.com
    secret-access: <secret-access/>
    access-key: <access-key/>
```

### Maven
```
    <!--...-->
    <dependencies>
        <dependency>
            <groupId>in.hocg.alioss</groupId>
            <artifactId>alioss-spring-boot-starter</artifactId>
            <version>0.1.0-SNAPSHOT</version>
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
