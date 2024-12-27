package potatowoong.pubsub.repository

import org.springframework.data.jpa.repository.JpaRepository
import potatowoong.pubsub.entity.PubSubLog

interface PubSubLogRepository : JpaRepository<PubSubLog, Long> {
}