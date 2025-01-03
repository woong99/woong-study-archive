package potatowoong.producerredisstream

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProducerRedisStreamApplication

fun main(args: Array<String>) {
    runApplication<ProducerRedisStreamApplication>(*args)
}
