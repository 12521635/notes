# MyBatis-Plus

Spring Boot 3 要用 `mybatis-plus-spring-boot3-starter`，不是老的 starter。

## 条件构造器

用 LambdaQueryWrapper，字段名有编译期检查：

```java
list(new LambdaQueryWrapper<User>()
        .like(StringUtils.hasText(name), User::getName, name)
        .ge(User::getAge, 18)
        .orderByDesc(User::getCreatedAt));
```

第一个 boolean 参数挺好用，条件为 false 就不拼这段 SQL，省一堆 if。

## 分页

分页插件要手动配拦截器，没配的话分页不生效，只查出全部数据：

```java
@Bean
public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
    return interceptor;
}
```

## 逻辑删除和自动填充

- 逻辑删除：yml 里配 `logic-delete-field: deleted`，实体加 `@TableLogic`，之后查询自动带 `deleted = 0`，删除变 update。
- 自动填充：createdAt/updatedAt 这种字段配 `@TableField(fill = ...)`，写个 MetaObjectHandler 统一填时间。

## 踩过的坑

- `updateById` 默认忽略 null 字段，想把字段更新成 null 得用 UpdateWrapper 的 set。
- 主键默认是雪花 ID，自增表要写 `@TableId(type = IdType.AUTO)`，不然插入报错。
- `new Page()` 参数顺序是 (current, size)，传反了查的就不对。
