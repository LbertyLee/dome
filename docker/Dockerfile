# 该镜像需要依赖的基础镜像
FROM openjdk:17
# 将当前目录下的jar包复制到docker容器的/目录下
ADD dome.jar /dome.jar
RUN bash -c 'touch /dome.jar'
# 声明服务运行在38089端口
EXPOSE 38089:
# 指定docker容器启动时运行jar包
ENTRYPOINT ["java", "-jar","/dome.jar"]

