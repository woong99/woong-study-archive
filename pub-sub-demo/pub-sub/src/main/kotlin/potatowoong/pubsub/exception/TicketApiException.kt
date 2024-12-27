package potatowoong.pubsub.exception

class TicketApiException(
    private val ticketId: Long
) : RuntimeException() {
}