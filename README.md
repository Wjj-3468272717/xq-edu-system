# p1--教务管理系统
基于SSMp的教务管理系统

## 1.项目介绍

### 1.1 项目总体功能介绍

![img00001](README.assets/img00001-1780377528183-4.jpg)

### 1.2 项目环境和技术栈

- 项目环境：IDEA开发工具、MySQL数据库8.0、Maven项目构建工具、jdk1.8
- 技术栈：
  - web框架：SpringMvc、Spring、MyBatis、MyBatisPlus 
  - 数据库：MySql 
  - 项目构建工具：Maven 
  - 前端模板：Thymeleaf 
  - 安全框架：Spring security 
  - 前端框架：BootStrap,Layui 
  - 数据图表：ECharts

## 2.项目准备

### 2.1 pom文件相关依赖

```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>

  <dependencies>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.3.2</version>
    </dependency>

    <!--AOP面向切面编程-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aspects</artifactId>
      <version>5.3.2</version>
    </dependency>

    <!--SpringMvc依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-web</artifactId>
      <version>5.3.2</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>5.3.2</version>
    </dependency>

    <!-- servlet依赖 -->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
      <scope>provided</scope>
    </dependency>

    <!-- lombok-->
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>1.18.12</version>
      <scope>provided</scope>
    </dependency>

    <!-- mysql驱动包 -->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.25</version>
    </dependency>

    <!--Druid数据库连接池-->
    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>1.1.10</version>
    </dependency>

    <!-- MyBatis plus 包含了MyBatis-->
    <dependency>
      <groupId>com.baomidou</groupId>
      <artifactId>mybatis-plus</artifactId>
      <version>3.1.1</version>
    </dependency>

    <!--Spring-jdbc 整合jdbc-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>5.3.2</version>
    </dependency>

    <!-- Spring测试模块和Junit4整合-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-test</artifactId>
      <version>5.3.2</version>
    </dependency>

    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>

    <!--面向切面编程依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aspects</artifactId>
      <version>5.3.2</version>
    </dependency>

    <!-- 日志工具-->
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-log4j12</artifactId>
      <version>1.7.22</version>
    </dependency>

    <dependency>
      <groupId>com.google.code.gson</groupId>
      <artifactId>gson</artifactId>
      <version>2.8.5</version>
    </dependency>

    <!--SpringMvc文件上传包-->
    <dependency>
      <groupId>org.apache.commons</groupId>
      <artifactId>commons-io</artifactId>
      <version>1.3.2</version>
    </dependency>

    <dependency>
      <groupId>commons-fileupload</groupId>
      <artifactId>commons-fileupload</artifactId>
      <version>1.3.1</version>
    </dependency>

    <!-- mybatis plus 代码生成器 -->
    <dependency>
      <groupId>com.baomidou</groupId>
      <artifactId>mybatis-plus-generator</artifactId>
      <version>3.2.0</version>
    </dependency>

    <dependency>
      <groupId>org.freemarker</groupId>
      <artifactId>freemarker</artifactId>
      <version>2.3.28</version>
    </dependency>

    <!-- >springSecurity核心依赖 -->
    <dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-config</artifactId>
      <version>5.1.5.RELEASE</version>
    </dependency>

    <dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-taglibs</artifactId>
      <version>5.1.5.RELEASE</version>
    </dependency>

    <dependency>
      <groupId>javax.annotation</groupId>
      <artifactId>jsr250-api</artifactId>
      <version>1.0</version>
    </dependency>

    <!-- thymeleaf模板 -->
    <dependency>
      <groupId>org.thymeleaf</groupId>
      <artifactId>thymeleaf</artifactId>
      <version>3.0.9.RELEASE</version>
    </dependency>

    <dependency>
      <groupId>org.thymeleaf</groupId>
      <artifactId>thymeleaf-spring5</artifactId>
      <version>3.0.9.RELEASE</version>
    </dependency>

    <!--spring-security thymeleaf标签库-->
    <dependency>
      <groupId>org.thymeleaf.extras</groupId>
      <artifactId>thymeleaf-extras-springsecurity5</artifactId>
      <version>3.0.4.RELEASE</version>
    </dependency>
  </dependencies>
```

### 2.2 数据库

运行/数据库脚本/edu_system.sql，创建database和tables

```properties
#db.properties

jdbc.username=       #youru sername 	
jdbc.password=       #your password
jdbc.driverClassName=com.mysql.cj.jdbc.Driver   #your driver
jdbc.url=jdbc:mysql://127.0.0.1:3306/edu?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true   #your url
```

```properties
#log4j.properties

# log4J日志配置 根日志器
# DEBUG 日志级别  控制台
# log4j提供8种日志级别(从OFF到ALL,常用6种),日志级别从高到低:OFF>FATAL>ERROR>WARN>INFO>DEBUG>
# TRACE> ALL
# Console 控制台输出
log4j.rootLogger=DEBUG, Console
log4j.appender.Console=org.apache.log4j.ConsoleAppender
log4j.appender.Console.layout=org.apache.log4j.PatternLayout
log4j.appender.Console.layout.ConversionPattern=%d [%t] %-5p [%c] - %m%n

# SQL日志输出
log4j.logger.java.sql.ResultSet=INFO
log4j.logger.org.apache=INFO
log4j.logger.java.sql.Connection=DEBUG
log4j.logger.java.sql.Statement=DEBUG
log4j.logger.java.sql.PreparedStatement=DEBUG
```

