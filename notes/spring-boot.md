# Spring Boot

## 注入

三种方式里构造器注入最好：依赖可以声明成 final、好写单测、依赖太多会直接暴露设计问题。Spring 4.3 之后只有一个构造器的话 `@Autowired` 都能省。

## Bean 生命周期

大致是：实例化 -> 属性填充 -> Aware 回调 -> BeanPostProcessor 前置 -> 初始化（@PostConstruct）-> BeanPostProcessor 后置 -> 使用 -> 销毁。AOP 代理对象一般是在后置处理器这一步生成的。

## 自动配置

`@SpringBootApplication` 里面 `@EnableAutoConfiguration` 会去读 `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`（2.7 之前是 spring.factories），再配合 `@ConditionalOnClass`、`@ConditionalOnMissingBean` 这些条件注解决定哪些配置生效。自定义 starter 就是写一个 AutoConfiguration 然后注册到 imports 文件里。

## AOP

拿 AOP 做了个接口耗时统计的练习，@Around 里 finally 里算时间差就行。

## 事务失效

踩过一次：同一个类里 methodA 调 methodB，methodB 上的 @Transactional 不生效，因为 this 调用不走代理。其他失效场景记一下：

- 方法不是 public
- 异常被 try-catch 吞了
- 抛的是受检异常（默认只回滚 RuntimeException 和 Error，最好写 `rollbackFor = Exception.class`）
- 类根本不是 Spring 管理的（手动 new 的）

## 配置绑定

少量配置用 `@Value`，成组的用 `@ConfigurationProperties`，能自动绑定类型还支持校验。多环境用 `spring.profiles.active` 切。
