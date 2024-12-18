package potatowoong.multiexternalapi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import potatowoong.multiexternalapi.service.MainService

@RestController
@RequestMapping("/")
class MainController(
    private val mainService: MainService
) {

    @GetMapping("/rest-template")
    fun restTemplate() {
        mainService.restTemplate()
    }

    @GetMapping("/web-client")
    fun webClient() {
        mainService.webClient()
    }

    @GetMapping("/coroutine")
    fun coroutine() {
        mainService.coroutine()
    }
}