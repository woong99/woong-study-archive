package potatowoong.pubsub

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.retry.annotation.EnableRetry
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@EnableAsync
@EnableRetry
@SpringBootApplication
class PubSubApplication

fun main(args: Array<String>) {
    runApplication<PubSubApplication>(*args)
}
