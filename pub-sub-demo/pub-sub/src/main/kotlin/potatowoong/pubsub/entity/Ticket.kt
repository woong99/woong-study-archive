package potatowoong.pubsub.entity

import jakarta.persistence.*
import potatowoong.pubsub.enums.Status

@Entity
class Ticket(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val ticketId: Long? = null,

    val code: String,

    @Enumerated(EnumType.STRING)
    var status: Status,

    var retryCount: Int
) {
    fun updateToSuccess() {
        this.status = Status.SUCCESS
    }

    fun updateToFail() {
        this.status = Status.FAIL
        this.retryCount++
    }

    companion object {
        fun of(
            code: String
        ) = Ticket(
            code = code,
            status = Status.INIT,
            retryCount = 0
        )
    }
}