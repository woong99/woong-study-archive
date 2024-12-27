package potatowoong.multiexternalapi.service

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.util.StopWatch
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class MainService(
    private val restTemplate: RestTemplate,
    private val webClient: WebClient
) {

    private val log = KotlinLogging.logger { }

    fun restTemplate() {
        val stopwatch = StopWatch()
        stopwatch.start()

        val result1 = restTemplate.exchange("http://localhost:8081/api/1", HttpMethod.GET, null, String::class.java)
        log.info { result1.body }

        val result2 = restTemplate.exchange("http://localhost:8081/api/2", HttpMethod.GET, null, String::class.java)
        log.info { result2.body }

        val result3 = restTemplate.exchange("http://localhost:8081/api/3", HttpMethod.GET, null, String::class.java)
        log.info { result3.body }

        val result4 = restTemplate.exchange("http://localhost:8081/api/4", HttpMethod.GET, null, String::class.java)
        log.info { result4.body }

        val result5 = restTemplate.exchange("http://localhost:8081/api/5", HttpMethod.GET, null, String::class.java)
        log.info { result5.body }

        stopwatch.stop()
        log.info { stopwatch.prettyPrint() }
    }

    fun webClient() {
        val stopwatch = StopWatch()
        stopwatch.start()

        // 각 요청을 비동기로 실행
        val result1 = webClient.get()
            .uri("/api/1")
            .retrieve()
            .bodyToMono(String::class.java)

        val result2 = webClient.get()
            .uri("/api/2")
            .retrieve()
            .bodyToMono(String::class.java)

        val result3 = webClient.get()
            .uri("/api/3")
            .retrieve()
            .bodyToMono(String::class.java)

        val result4 = webClient.get()
            .uri("/api/4")
            .retrieve()
            .bodyToMono(String::class.java)

        val result5 = webClient.get()
            .uri("/api/5")
            .retrieve()
            .bodyToMono(String::class.java)

        // 모든 요청을 zip으로 결합하여 실행
        Mono.zip(result1, result2, result3, result4, result5)
            .doOnSubscribe { log.info { "Requests started." } }
            .doOnSuccess { log.info { "Requests completed." } }
            .doOnError { error -> log.error { "Error occurred: $error" } }
            .subscribe { results ->
                log.info { results }
                stopwatch.stop()
                log.info { stopwatch.prettyPrint() }
            }
    }

    suspend fun coroutine() {
        val stopwatch = StopWatch()
        stopwatch.start()

        runBlocking {
            val result1 = async {
                log.info { "result1" }
                val response =
                    restTemplate.exchange("http://localhost:8081/api/1", HttpMethod.GET, null, String::class.java)
                log.info { "result1.body ${response.body}" }
                response.body
            }

            val result2 = async {
                log.info { "result2" }
                val response =
                    restTemplate.exchange("http://localhost:8081/api/2", HttpMethod.GET, null, String::class.java)
                log.info { response.body }
                response.body
            }

            val result3 = async {
                log.info { "result3" }
                val response =
                    restTemplate.exchange("http://localhost:8081/api/3", HttpMethod.GET, null, String::class.java)
                log.info { response.body }
                response.body
            }

            val result4 = async {
                log.info { "result4" }
                val response =
                    restTemplate.exchange("http://localhost:8081/api/4", HttpMethod.GET, null, String::class.java)
                log.info { response.body }
                response.body
            }

            val result5 = async {
                log.info { "result5" }
                val response =
                    restTemplate.exchange("http://localhost:8081/api/5", HttpMethod.GET, null, String::class.java)
                log.info { response.body }
                response.body
            }

            // 모든 비동기 작업이 완료될 때까지 기다림
            val responses = awaitAll(result1, result2, result3, result4, result5)
            log.info { responses }
        }

        stopwatch.stop()
        log.info { stopwatch.prettyPrint() }
    }

    suspend fun test1() {
        delay(2000)
        log.info { "test1" }
    }

    suspend fun test2() {
        delay(1000)
        log.info { "test2" }
    }

    suspend fun test3() {
        delay(3000)
        log.info { "test3" }
    }

    suspend fun test4() {
        delay(1000)
        log.info { "test4" }
    }

    suspend fun test5() {
        delay(1000)
        log.info { "test5" }
    }
}