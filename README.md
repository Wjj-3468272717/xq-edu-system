# p1--教务管理系统
基于SSMp的教务管理系统

## 1.总体介绍

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

## 2.环境搭建

### 2.1 配置文件编写

#### 2.1.1 pom.xml

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

#### 2.1.2 db.properties

运行/数据库脚本/edu_system.sql，创建database和tables

```properties
#db.properties

jdbc.username=       #youru sername 	
jdbc.password=       #your password
jdbc.driverClassName=com.mysql.cj.jdbc.Driver   #your driver
jdbc.url=jdbc:mysql://127.0.0.1:3306/edu?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true   #your url

```

#### 2.1.3 log4j.properties


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

#### 2.1.4 mybatis.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <typeAliases>
        <package name="com.v1.pojo"/>
    </typeAliases>

    <plugins>
        <plugin interceptor="com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor"/>
    </plugins>

    <mappers>
        <package name="com.v1.mapper"/>
    </mappers>
</configuration>
```

#### 2.1.5 spring.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           https://www.springframework.org/schema/context/spring-context.xsd
           http://www.springframework.org/schema/tx
           http://www.springframework.org/schema/tx/spring-tx.xsd
           http://www.springframework.org/schema/aop
           https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--1.加载外部属性文件-->
    <context:property-placeholder location="classpath*:db.properties"
                                  file-encoding="UTF-8"
                                  ignore-resource-not-found="true"/>

    <!--2.数据源-->
    <bean id="ds" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${jdbc.driverClassName}"/>
        <property name="password" value="${jdbc.password}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="url" value="${jdbc.url}"/>
    </bean>

    <!--3.SqlSession工厂 MyBatis Plus使用MybatisSqlSessionFactoryBean-->
    <bean class="com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean">
        <property name="dataSource" ref="ds"/>
        <property name="configLocation" value="classpath:mybatis.xml"/>
    </bean>

    <!--4.Mapper扫描-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.v1.mapper"/>
    </bean>

    <!--5.声明式事务 事务平台管理器-->
    <bean id="transactionManager"
          class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="ds"/>
    </bean>

    <!--6.开启事务的注解支持 @Transactional-->
    <tx:annotation-driven transaction-manager="transactionManager"/>

    <!--7.包扫描-->
    <context:component-scan base-package="com.v1"/>

</beans>
```

#### 2.1.6 spring-mvc.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           https://www.springframework.org/schema/context/spring-context.xsd
           http://www.springframework.org/schema/mvc
           https://www.springframework.org/schema/mvc/spring-mvc.xsd
           http://www.springframework.org/schema/tx
           http://www.springframework.org/schema/tx/spring-tx.xsd
           http://www.springframework.org/schema/aop
           https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!-- 扫描控制器 -->
    <context:component-scan base-package="com.v1.controller"/>

    <!-- 静态资源放行：把静态资源交给Tomcat默认的Servlet处理 -->
    <mvc:default-servlet-handler/>

    <!-- 开启注解驱动 -->
    <mvc:annotation-driven/>

    <!-- 配置文件上传视图解析器 -->
    <!-- id的值是固定的，SpringMVC程序内部使用对象名 -->
    <bean id="multipartResolver"
          class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <!-- 设置上传文件的最大尺寸为5MB（字节为单位） -->
        <property name="maxUploadSize" value="5242880"/>
    </bean>

</beans>
```

#### 2.1.7 web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
         http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

  <display-name>Archetype Created Web Application</display-name>

  <!-- Web初始化参数：指定Spring配置文件位置 -->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath*:spring.xml</param-value>
  </context-param>

  <!-- Spring MVC 乱码过滤器 -->
  <filter>
    <filter-name>encodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value>
    </init-param>
    <!-- 强制使用指定编码 -->
    <init-param>
      <param-name>forceEncoding</param-name>
      <param-value>true</param-value>
    </init-param>
  </filter>

  <filter-mapping>
    <filter-name>encodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>

  <!-- ContextLoaderListener：监听Web项目启动事件，随之启动Spring框架 -->
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>

  <!-- 配置Spring MVC核心控制器 -->
  <servlet>
    <servlet-name>DispatcherServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:spring-mvc.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>

  <servlet-mapping>
    <servlet-name>DispatcherServlet</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>

</web-app>
```

### 2.2 Mybatis-plus代码生成器

[mp官网]: https://baomidou.com/guides/code-generator/ "代码生成器指南"

参考[mp官网]获取详细配置。

```java
package com.v1;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine; // 修正1：类名正确拼写

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir"); // 获取当前项目的路径
        // 注意：如果路径包含中文或空格，建议使用项目根路径
        gc.setOutputDir(projectPath + "/src/main/java"); // 真正生成的路径
        gc.setAuthor("v1"); // 作者信息
        gc.setOpen(false); // 不用打开代码生成的文件夹窗口
//        gc.setSwagger2(true); // 可选：开启Swagger2注解支持
        // gc.setFileOverride(true); // 可选：覆盖已有文件
        gc.setServiceName("%sService"); // 设置Service接口名称，去掉I前缀
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/edu?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.v1");
        pc.setEntity("pojo");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller"); // 建议添加controller包配置
        mpg.setPackageInfo(pc);

        // 模板配置
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null); // 不生成XML文件
        // templateConfig.setController("/templates/controller.java"); // 自定义controller模板
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel); // 开启驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true); // Controller映射使用连字符
        strategy.setEntityLombokModel(true); // 使用Lombok注解
        strategy.setRestControllerStyle(true); // 生成@RestController注解
        mpg.setStrategy(strategy);

        // 设置模板引擎
        mpg.setTemplateEngine(new FreemarkerTemplateEngine()); // 修正2：类名正确拼写

        // 执行生成
        mpg.execute();
    }
}
```

### 2.3 Thymeleaf模板

在spring-mvc.xml中添加如下配置：

```xml
 <!-- 模板引擎解析器 -->
    <bean id="templateResolver"
          class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver">
        <property name="prefix" value="/WEB-INF/templates/"/>
        <property name="suffix" value=".html"/>
        <!-- 模板引擎使用严格的HTML语法 -->
        <property name="templateMode" value="HTML"/>
        <!-- 模板引擎要不要缓存（开发时设为false，生产环境设为true） -->
        <property name="cacheable" value="false"/>
        <property name="characterEncoding" value="UTF-8"/>
    </bean>

    <!-- 模板引擎 -->
    <bean id="templateEngine"
          class="org.thymeleaf.spring5.SpringTemplateEngine">
        <property name="templateResolver" ref="templateResolver"/>
        <!-- 配置Thymeleaf标签库 -->
        <property name="additionalDialects">
            <set>
                <bean class="org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect"/>
            </set>
        </property>
    </bean>

    <!-- Thymeleaf模板引擎整合到Spring环境中 -->
    <bean class="org.thymeleaf.spring5.view.ThymeleafViewResolver">
        <property name="templateEngine" ref="templateEngine"/>
        <!-- 中文乱码问题 -->
        <property name="characterEncoding" value="UTF-8"/>
        <!-- 设置响应内容类型 -->
        <property name="contentType" value="text/html; charset=UTF-8"/>
        <!-- 视图名称顺序（可选） -->
        <property name="order" value="1"/>
    </bean>
```

测试Thymeleaf是否可用：

```java
    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model){
        model.addAttribute("userName","eric");
        return "hello";
    }
```

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h5>hello Thymeleaf</h5>
    <p th:text="${userName}"></p>
</body>
</html>
```

重启tomcat，发送请求，查看页面是否渲染数据

http://localhost:8888/membertype/testThymeleaf

## 3. 会员卡功能模块实现
