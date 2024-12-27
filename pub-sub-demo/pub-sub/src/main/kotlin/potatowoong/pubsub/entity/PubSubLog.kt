package potatowoong.pubsub.entity

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
class PubSubLog(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val logId: Long? = null,

    val port: String,

    val ticketId: Long,
) {
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATETIME")
    @ColumnDefault("CURRENT_TIMESTAMP")
    lateinit var createdAt: LocalDateTime
}