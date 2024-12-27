package potatowoong.pubsub.service

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import potatowoong.pubsub.entity.Ticket
import potatowoong.pubsub.repository.TicketRepository
import potatowoong.pubsub.utils.RedisPublisher

@Service
class TicketApiService(
    private val ticketRepository: TicketRepository,
    private val restTemplate: RestTemplate,
    private val publisher: ApplicationEventPublisher,
    private val redisPublisher: RedisPublisher
) {

    private val log = KotlinLogging.logger { }

    fun publishTicket() {
        val ticket = callApi()

        log.info { "Publish - ${Thread.currentThread().id}" }
        publisher.publishEvent(ticket.ticketId!!)
    }

    fun publishTicketWithRedis() {
        val ticket = callApi()

        log.info { "Publish - ${Thread.currentThread().id}" }
        redisPublisher.publish(ChannelTopic.of("ticket"), ticket.ticketId.toString())
    }

    fun callApi(): Ticket {
        val result = restTemplate.exchange<String>("http://localhost:8082/api", HttpMethod.GET)
        val ticket = Ticket.of(result.body!!)
        ticketRepository.save(ticket)

        return ticket
    }
}