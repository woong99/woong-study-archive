package potatowoong.consumerredisstream

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ConsumerRedisStreamApplication

fun main(args: Array<String>) {
    runApplication<ConsumerRedisStreamApplication>(*args)
}
