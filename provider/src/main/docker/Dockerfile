# 基于哪个镜像
FROM java:8
# 作者
MAINTAINER zsw
# 将本地文件夹挂载到当前容器
VOLUME /tmp
# 将jar包添加到容器中并更名为pp-dashboard.jar，adshboard-0.0.1-SNAPSHOT.jar这里是maven打包后的名字
ADD provider-0.0.1-SNAPSHOT.jar app-provider01.jar
RUN bash -c 'touch /app.jar'
# 配置容器启动后执行的命令
ENTRYPOINT  ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app-provider01.jar"]