## 前言

简单的搭建Springboot整合Dubbo和Zookeeper的Demo，就是一个入门级别的小玩意，希望可以给初学的人一点启发。

## 准备工作

工具：Idea、Zookeeper

## 一、配置Provider

1、添加pom.xml的依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>2.1.1.RELEASE</version>
</dependency>

<dependency>
    <groupId>com.alibaba.boot</groupId>
    <artifactId>dubbo-spring-boot-starter</artifactId>
    <version>0.2.0</version>
</dependency>

<dependency>
    <groupId>org.apache.zookeeper</groupId>
    <artifactId>zookeeper</artifactId>
    <version>3.4.13</version>
    <type>pom</type>
</dependency>
```

2、配置yml配置文件

```yaml
server:
  port: 8081
dubbo:
  application:
    name: dubbo-provider
  registry:
    address: zookeeper://127.0.0.1:2181
  protocol:
    name: dubbo
    port: 20880
```

3、创建Provider的Service

```java
public interface ProviderService {

    /**
     * hello
     * @param name 名字
     * @return name
     */
    public String sayHello(String name);
}
```

```java
@Service
public class ProviderServiceImpl implements ProviderService {
    /**
     * hello
     * @param name 名字
     * @return name
     */
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
```

<font color="red">@Service:是引入的Dubbo的注解，注意不要引用错了。</font>

## 二、配置Consumer

1、添加pom.xml的依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>2.1.1.RELEASE</version>
</dependency>

<dependency>
    <groupId>com.alibaba.boot</groupId>
    <artifactId>dubbo-spring-boot-starter</artifactId>
    <version>0.2.0</version>
</dependency>

<dependency>
    <groupId>org.apache.zookeeper</groupId>
    <artifactId>zookeeper</artifactId>
    <version>3.4.13</version>
    <type>pom</type>
</dependency>
```

2、配置yml配置文件

```yaml
server:
  port: 8080
dubbo:
  application:
    name: dubbo-consumer
  registry:
    address: zookeeper://127.0.0.1:2181
  protocol:
    name: dubbo
    port: 20880
```

3、创建Service（ProviderService、ConsumerService）

（1）创建ProviderService

```java
public interface ProviderService {
    /**
     * 说hello
     * @param name 名字
     * @return null
     */
    public String sayHello(String name);
}
```

（2）创建ConsumerService

```java
@Service
public class ConsumerService {

    @Reference
    private ProviderService providerService;

    public String hello(String name){
        return providerService.sayHello(name);
    }
}
```

<font color = "red">@Reference:是Dubbo的注解，注意引包。</font>

4、创建测试的Controller

```java
@RestController
public class ConsumerController {

    @Resource
    ConsumerService consumerService;

    @RequestMapping("hello")
    public String hello(){
        return consumerService.hello("Tom");
    }
}
```

## 三、测试服务

启动两个服务，浏览器测试：http://localhost:8080/hello
