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
