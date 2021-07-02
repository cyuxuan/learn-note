# SpringCloud第二季
1. 课程内容(springCloud+springCloud alibaba)
2. 技术要求
    java8 + maven + git / github + Nginx + RabbitMQ + SpringBoot2.0

### 分布式服务技术
- 服务注册与发现
- 服务调用
- 服务熔断
- 负载均衡
- 服务降级
- 服务消息队列
- 配置中心管理
- 服务网关
- 服务监控
- 全链路追踪
- 自动化构建部署
- 服务定时任务调度操作


### SpringCloud = 分布式微服务架构的一站式解决方案，是多种微服务架构落地技术的集合体。

### 关于Cloud各种组件的停更/升级/替换
- 停更不停用
    1. 被动修复bugs
    2. 不再接受合并请求
    3. 不再发布新版本
- Cloud升级 明细条目
    1. 服务注册中心：
        - Eureka (放弃)
        - Zookeeper (技术保守推荐)
        - Consul (不推荐)
        - Nacos (重要)
    2. 服务调用
        - Ribbon (轻度放弃)
        - LoadBalancer (Ribbon替换者，还不成熟)
    3. 服务调用2
        - Feign (放弃)
        - OpenFegin 
    4. 服务降级
        - Hystrix (放弃，但是还是用有很多公司使用)
        - resilience4j (官网推荐，国内很少使用)
        - sentinel (强烈推荐)
    5. 服务网关
        - Zuul (放弃)
        - Zuul2 (未出现)
        - gateway (主流)
    6. 服务配置
        - Config (放弃)
        - Nacos (推荐)
    7. 服务总线
        - Bus (放弃)
        - Nacos (推荐)
### 开始项目


### 微服务架构编码构建
#### 约定 > 配置 > 编码
#### idea新建project工作空间
##### 微服务cloud整体聚合父工程Project
###### 父工程步骤
1. New Project
    1. 选择工程 Maven -> site
2. 聚合总父工程名字
3. Maven选版本
    - 3.5以上
4. 工程名字
5. 字符编码
    - 设置 -> General -> File Encodings(此处可修改编码，一般全部都是utf-8)
    - ![Image text](images/image-1.png)
6. 注解生效激活
    - ![Image text](images/image-2.png)
7. java编译版本选8
8. File Type过滤
- Setting -> File Types下过滤不想看见的文件  
9. 父工程pom
```
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>cn.cyuxuan</groupId>
    <artifactId>learn-1</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <modules>

    </modules>

    <!-- 统一管理jar包版本 -->
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <junit.version>4.12</junit.version>
        <log4j.version>1.2.17</log4j.version>
        <lombok.version>1.16.18</lombok.version>
        <mysql.version>5.1.47</mysql.version>
        <druid.version>1.1.16</druid.version>
        <mybatis.spring.boot.version>1.3.0</mybatis.spring.boot.version>
    </properties>

    <!-- dependencyManagement 子模块继承之后，提供作用：锁定版本+子modlue不用写groupId和version  -->
    <dependencyManagement>
        <dependencies>
            <!--spring boot 2.2.2-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>2.2.2.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--spring cloud Hoxton.SR1-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Hoxton.SR1</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--spring cloud alibaba 2.1.0.RELEASE-->
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>2.1.0.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>${mysql.version}</version>
            </dependency>
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>${druid.version}</version>
            </dependency>
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>${mybatis.spring.boot.version}</version>
            </dependency>
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>${junit.version}</version>
            </dependency>
            <dependency>
                <groupId>log4j</groupId>
                <artifactId>log4j</artifactId>
                <version>${log4j.version}</version>
            </dependency>
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>${lombok.version}</version>
                <optional>true</optional>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <fork>true</fork>
                    <addResources>true</addResources>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```
10. Maven工程细节
- Maven中的dependencyManagement与dependency
    - 使用pom.xml中的dependencyManagement元素能让所有子项目中引用一个依赖而不用显示的列出版本号。Maven 会沿着父子层次向上走，直到找到一个拥有dependencyManagement元素的项目，然后它就会使用这个dependencyManagement元素中指定的版本号。
    - dependencyManagement中只是定义，真正的引入依赖还是在子工程中
- Maven中跳过单元测试
    - 取消Maven闪电标志
- 父工程创建完成执行 ``mvn:install`` 将父工程发布到仓库方便子工程继承
    - [Maven配置参考](https://blog.csdn.net/weixin_44005516/article/details/108293228)
#### Rest微服务工程构建
##### 构建步骤
###### cloud-provider-payment-8001
1. 建cloud-provider-payment-8001
2. 改POM
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>learn-1</artifactId>
        <groupId>cn.cyuxuan</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-provider-payment-8001</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <!--包含了sleuth+zipkin-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zipkin</artifactId>
        </dependency>
        <!--eureka-client-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>
        <!--数据库连接池-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.10</version>
        </dependency>
        <!--mysql-connector-java-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <!--jdbc-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```
3. 写YML
```
server:
  port: 8001

spring:
  application:
    name: cloud-payment-service #微服务名称
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource  #当前数据源操作类型
    driver-class-name: org.gjt.mm.mysql.Driver #mysql驱动包
    url: jdbc:mysql://cyuxuan.cn:3306/springcloud-learn-1-db?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: root
    password: 123456

mybatis:
  mapper-locations: classpath:mapper/*.xml #mybatis mapper文件扫描位置
  type-aliases-package: cn.cyuxuan.entites #所有Entity别名类所在包
```
4. 主启动
5. 业务类
6. 测试
*** 
linux 安装mysql
- [linux安装Mysql参考](https://blog.csdn.net/qq_41510551/article/details/110731610)
- [ARM64架构下面安装mysql](https://blog.csdn.net/littleluoli/article/details/104796805/)
- [linux安装mysql参考](https://blog.csdn.net/sinat_15946141/article/details/105314944)

