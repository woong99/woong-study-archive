package potatowoong.pubsubapi

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api")
class ApiController {

    @GetMapping
    fun api():ResponseEntity<String> {
        Thread.sleep(2000)
        return ResponseEntity.ok(UUID.randomUUID().toString().replace("-", ""))
    }
}