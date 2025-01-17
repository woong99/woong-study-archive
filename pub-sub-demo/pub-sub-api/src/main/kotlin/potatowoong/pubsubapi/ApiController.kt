package potatowoong.pubsubapi

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import kotlin.random.Random

@RestController
@RequestMapping("/api")
class ApiController {

    private val log = KotlinLogging.logger { }

    @GetMapping
    fun api(): ResponseEntity<String> {
        Thread.sleep(2000)

        return ResponseEntity.ok(UUID.randomUUID().toString().replace("-", ""))
    }

    @GetMapping("/ticket/{ticketId}/{port}")
    fun ticketProcess(
        @PathVariable("ticketId") ticketId: Long,
        @PathVariable("port") port: String
    ): ResponseEntity<String> {
        log.info { "Ticket Process - $ticketId - $port" }
        Thread.sleep(1500)

        // 테스트 상황을 구현하기 위해 랜덤하게 예외를 발생
        if (Random.nextInt(1, 4) == 3) {
            return ResponseEntity.ok("FAIL")
        }

        return ResponseEntity.ok("OK")
    }
}