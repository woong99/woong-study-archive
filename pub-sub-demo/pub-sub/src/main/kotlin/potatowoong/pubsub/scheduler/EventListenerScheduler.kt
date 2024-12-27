package potatowoong.pubsub.scheduler

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import potatowoong.pubsub.service.TicketApiService

@Component
class EventListenerScheduler(
    private val ticketApiService: TicketApiService
) {

    @Scheduled(fixedDelay = 3000, initialDelay = 1000)
    fun callApi() {
        ticketApiService.publishTicket()
    }
}