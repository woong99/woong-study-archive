package potatowoong.pubsub.service

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpMethod
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import potatowoong.pubsub.exception.TicketApiException
import potatowoong.pubsub.repository.TicketRepository

@Service
class TicketService(
    private val ticketRepository: TicketRepository,
    private val restTemplate: RestTemplate
) {

    private val log = KotlinLogging.logger { }

    @Retryable(maxAttempts = 3, value = [Exception::class])
    @Transactional(noRollbackFor = [TicketApiException::class])
    fun successTicket(ticketId: Long) {
        try {
            // Ticket 후처리 API 호출
            val result = restTemplate.exchange<String>("http://localhost:8082/api/ticket", HttpMethod.GET)
            log.info { "result: ${result.body}" }
            if (result.body == "FAIL") {
                throw TicketApiException(ticketId)
            }

            // Ticket 후처리 후 상태 변경
            val ticket = ticketRepository.findByIdOrNull(ticketId) ?: throw IllegalArgumentException("Ticket not found")
            ticket.updateToSuccess()
            ticketRepository.save(ticket)
        } catch (e: Exception) {
            log.error { "Error occurred while processing ticket!!: $ticketId" }
            failTicket(ticketId)
            throw e
        }
    }

    private fun failTicket(ticketId: Long) {
        val ticket = ticketRepository.findByIdOrNull(ticketId) ?: throw IllegalArgumentException("Ticket not found")
        ticket.updateToFail()
        ticketRepository.save(ticket)
    }
}