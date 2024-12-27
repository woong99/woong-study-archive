package potatowoong.pubsub.repository

import org.springframework.data.jpa.repository.JpaRepository
import potatowoong.pubsub.entity.Ticket

interface TicketRepository : JpaRepository<Ticket, Long> {

}