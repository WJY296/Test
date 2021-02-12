package com.wjy

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.environment.CheckpointConfig
import org.apache.flink.streaming.api.environment.CheckpointConfig.ExternalizedCheckpointCleanup
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer, KafkaDeserializationSchema}
import org.apache.kafka.clients.consumer.ConsumerRecord

object TestJob {
  def main(args: Array[String]): Unit = {
    //运行环境
    val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    //设置checkpoint
    environment.enableCheckpointing(5000, CheckpointingMode.EXACTLY_ONCE)
    val config: CheckpointConfig = environment.getCheckpointConfig
    config.setCheckpointTimeout(4000)
    config.setMinPauseBetweenCheckpoints(200)
    config.setTolerableCheckpointFailureNumber(2)
    config.enableExternalizedCheckpoints(ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION)

    //连接kafka的参数
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "hadoop10:9092")


    //从数据源获取数据
    val value: DataStream[(String, String, Int, Long)] = environment.addSource(new FlinkKafkaConsumer[(String, String, Int, Long)]("topicA", new MyKafkaDeserializationSchema(), properties))

    value.print()

    environment.execute("Test_FlinkKafkaCheckpoint")


  }


}

class MyKafkaDeserializationSchema extends KafkaDeserializationSchema[(String, String, Int, Long)] {
  override def isEndOfStream(t: (String, String, Int, Long)): Boolean = false

  override def deserialize(consumerRecord: ConsumerRecord[Array[Byte], Array[Byte]]): (String, String, Int, Long) = {
    if (consumerRecord.key() != null) {

      (new String(consumerRecord.key()), new String(consumerRecord.value()), consumerRecord.partition(), consumerRecord.offset())
    } else {
      (null, new String(consumerRecord.value()), consumerRecord.partition(), consumerRecord.offset())

    }
  }

  override def getProducedType: TypeInformation[(String, String, Int, Long)] = {
    createTypeInformation[(String, String, Int, Long)];
  }
}

