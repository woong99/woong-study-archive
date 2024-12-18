package potatowoong.multiexternalapi.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ExternalApiController {

    private val log = KotlinLogging.logger { }

    @GetMapping("/1")
    fun getApi1(): String {
        Thread.sleep(3000)

        return "api1"
    }

    @GetMapping("/2")
    fun getApi2(): String {
        Thread.sleep(3000)

        return "api2"
    }

    @GetMapping("/3")
    fun getApi3(): String {
        Thread.sleep(3000)

        return "api3"
    }

    @GetMapping("/4")
    fun getApi4(): String {
        Thread.sleep(3000)

        return "api4"
    }

    @GetMapping("/5")
    fun getApi5(): String {
        Thread.sleep(3000)

        return "api5"
    }
}