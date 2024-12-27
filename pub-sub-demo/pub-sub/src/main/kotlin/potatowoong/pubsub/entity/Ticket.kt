package potatowoong.pubsub.entity

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import potatowoong.pubsub.enums.Status
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
class Ticket(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val ticketId: Long? = null,

    val code: String,

    @Enumerated(EnumType.STRING)
    var status: Status,

    var retryCount: Int,
) {
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATETIME")
    @ColumnDefault("CURRENT_TIMESTAMP")
    lateinit var createdAt: LocalDateTime

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME")
    @ColumnDefault("CURRENT_TIMESTAMP")
    lateinit var updatedAt: LocalDateTime

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