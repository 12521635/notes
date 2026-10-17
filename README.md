# notes

个人的后端学习笔记和一些练习代码。

## 目录

- `notes/` 学习笔记，md 格式
- `practice/java-basics/` 几个能直接跑的小 demo
- `practice/springboot-demo/` Spring Boot 练习项目，笔记 CRUD 接口

## 跑起来

springboot-demo（Spring Boot 3 + MyBatis-Plus + H2，不用装数据库）：

```bash
cd practice/springboot-demo
mvn spring-boot:run
# 测试
mvn test
```

java-basics：

```bash
cd practice/java-basics
javac StreamDemo.java && java StreamDemo
```
