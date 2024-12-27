package potatowoong.pubsubapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PubSubApiApplication

fun main(args: Array<String>) {
    runApplication<PubSubApiApplication>(*args)
}
