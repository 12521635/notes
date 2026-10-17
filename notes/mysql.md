# MySQL

## 索引

为什么用 B+ 树：树矮，三四层能撑千万数据，IO 少；叶子节点串成链表，范围查询方便。

几个词的含义记一下：

- 回表：二级索引叶子存的是主键，查别的列得再查一次聚簇索引
- 覆盖索引：要查的列索引里都有，不用回表，EXPLAIN 里显示 Using index
- 最左前缀：联合索引 (a, b, c) 只有从 a 开始查才走索引

失效的常见姿势：列上套函数、varchar 列用数字比（隐式转换）、`like '%xx'` 前模糊、or 两边有一个没索引。

## 事务

InnoDB 怎么实现 ACID：undo log 保证原子性，redo log 保证持久性，隔离性靠锁 + MVCC。

MVCC 简单说就是每行带着事务 id 和版本链，读的时候拿 ReadView 去版本链上找自己可见的版本。RC 每次读都生成新的 ReadView，RR 只在第一次读生成，所以 RR 能重复读。

注意一点：更新语句的 where 条件没走索引的话，行锁会退化成锁全表，写业务 SQL 时要留意。

## 慢 SQL

EXPLAIN 主要看 type（ALL 就是全表扫）、key（实际用的索引）、Extra（Using filesort / Using temporary 要警惕）。

深分页 LIMIT 1000000, 10 会扫描前一百万行再丢掉，改成子查询先定位起始 id，或者用游标方式 where id > 上一页最后的 id。
