package potatowoong.pubsub.listener

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Component
import potatowoong.pubsub.entity.PubSubLog
import potatowoong.pubsub.repository.PubSubLogRepository
import potatowoong.pubsub.service.TicketService

@Component
class RedisTicketEventListener(
    private val objectMapper: ObjectMapper,
    private val ticketService: TicketService,
    private val pubSubLogRepository: PubSubLogRepository,
    @Value("\${server.port}")
    private val port: String
) : MessageListener {

    private val log = KotlinLogging.logger { }

    override fun onMessage(message: Message, pattern: ByteArray?) {
        val ticketId = objectMapper.readValue(message.body, Long::class.java)
        pubSubLogRepository.save(
            PubSubLog(
                port = port,
                ticketId = ticketId
            )
        )
        log.info { "Received ticketId: $ticketId" }
        ticketService.successTicket(ticketId)
    }
}