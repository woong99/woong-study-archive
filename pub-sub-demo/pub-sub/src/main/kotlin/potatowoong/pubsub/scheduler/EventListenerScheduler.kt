package potatowoong.pubsub.scheduler

import org.springframework.context.annotation.Profile
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import potatowoong.pubsub.service.TicketApiService

@Component
@Profile("scheduler")
class EventListenerScheduler(
    private val ticketApiService: TicketApiService
) {
//    @Scheduled(fixedDelay = 3000, initialDelay = 1000)
//    fun callApi() {
//        ticketApiService.publishTicket()
//    }

    @Scheduled(fixedDelay = 3000, initialDelay = 1000)
    fun callApiWithRedis() {
        ticketApiService.publishTicketWithRedis()
    }
}