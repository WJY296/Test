### 本项目是测试Flink消费Kafka数据，设置了checkpoint后,状态恢复时从哪里开始消费



### 测试结果如下：

![Flink测试日志](.\Flink测试日志.png)

### 结论：
Flink消费kafka中的数据时，设置了checkpoint。如果checkpoint时，消费到100，做完checkpoint后继续消费到130,在使用check恢复时会从101开始重新消费。会有重复消费的问题。

