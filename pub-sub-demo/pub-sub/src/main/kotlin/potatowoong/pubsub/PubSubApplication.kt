package potatowoong.pubsub

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PubSubApplication

fun main(args: Array<String>) {
    runApplication<PubSubApplication>(*args)
}
