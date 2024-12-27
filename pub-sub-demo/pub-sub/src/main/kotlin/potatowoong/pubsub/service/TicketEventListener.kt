package potatowoong.pubsub.service

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.context.PayloadApplicationEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class TicketEventListener(
    private val ticketService: TicketService
) {

    private val log = KotlinLogging.logger { }

    @Async
    @EventListener
    fun handleEvent(event: PayloadApplicationEvent<Long>) {
        val ticketId = event.payload
        log.info { "Received event: $ticketId - ${Thread.currentThread().id}" }

        ticketService.successTicket(ticketId)
    }
}