# Spring Cloud / Nacos

## 版本

Spring Boot、Spring Cloud、Spring Cloud Alibaba 三个版本必须配套，直接照官方 Wiki 的版本对照表选，别自己凑。

## 注册中心

服务启动时把服务名、IP、端口注册上去，每 5 秒心跳。消费方拉服务列表缓存到本地，调用时负载均衡选一个。

临时实例（默认）走 AP，心跳超时就剔除；持久实例走 CP（Raft），不健康只会被标记。

```yaml
spring:
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
        namespace: dev   # 环境隔离用
```

## 配置中心

DataId 就是 `应用名-profile.yaml`。动态刷新两种写法：

- `@Value` 注入的类上加 `@RefreshScope`
- `@ConfigurationProperties` 绑定的 Bean 天然支持刷新，推荐这种

踩坑：配置死活不生效，最后发现 namespace 不一致，服务和配置必须在同一个 namespace。
