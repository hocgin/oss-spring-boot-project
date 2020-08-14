# ===========================
# 使用指定版本进行打包
# ===========================
#!/usr/bin/env bash
mvn -Drevision=1.0.0-SNAPSHOT clean package -DskipTests
