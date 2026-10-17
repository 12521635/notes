# Java 基础

## 集合

- ArrayList 扩容 1.5 倍：`oldCap + (oldCap >> 1)`，默认容量 10，中间插删要挪元素。
- HashMap（1.8）数组 + 链表 + 红黑树。链表长度超过 8 且数组容量到 64 才树化，退化阈值 6。容量始终是 2 的幂，这样取下标可以 `(n - 1) & hash` 代替取模。hash 还会再扰动一下（高 16 位异或），减少碰撞。
- ConcurrentHashMap 1.8 放弃分段锁，改成 CAS + synchronized 锁桶头节点，粒度更细。

## 异常

受检异常强制处理很烦但设计上是合理的。资源关闭现在都写 try-with-resources，别再手写 finally 关流了。捕获之后要么处理要么往上抛，只 printStackTrace 等于没处理。

## Stream

常用的就这几个，记个例子：

```java
Map<String, Double> avgByDept = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDept,
                Collectors.averagingDouble(Employee::getSalary)));
```

中间操作是惰性的，不写终结操作不执行。Stream 只能消费一次。

## 并发

- 线程池七个参数，执行顺序：核心线程 -> 队列 -> 非核心线程 -> 拒绝策略。
- `Executors` 那几个工厂方法别用，`newFixedThreadPool` 是无界队列，会 OOM，老老实实 `new ThreadPoolExecutor`。
- `volatile` 只保证可见性和禁止重排，`i++` 该不安全还是不安全。
- `ThreadLocal` 用完要 `remove()`，不然线程池场景下 value 一直被线程持有，会泄漏。

## JVM

堆分新生代（Eden + 两个 Survivor，8:1:1）和老年代，15 次 Minor GC 进老年代。收集器知道 G1（JDK 9+ 默认）和 ZGC（低延迟）就够了，细节以后用到再查。

## 踩坑

- `Integer` 缓存 -128~127，所以 127 == 127 是 true，128 == 128 是 false。包装类型比较一律 equals。
- `Arrays.asList` 拿到的列表不能 add。
- 循环里拼字符串用 StringBuilder，`+` 每次都会 new 对象。
