package potatowoong.multiexternalapi

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import potatowoong.multiexternalapi.service.MainService

@SpringBootTest
class MultiExternalApiApplicationTests(
    @Autowired
    private val mainService: MainService
) {

    @Test
    fun contextLoads() {
        mainService.restTemplate()
    }

}
