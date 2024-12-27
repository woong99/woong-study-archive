package potatowoong.pubsub.utils

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Component

@Component
class RedisPublisher(
    private val template: RedisTemplate<String, Any>
) {

    /**
     * String 메시지를 Redis Pub/Sub을 통해 발행
     */
    fun publish(topic: ChannelTopic, message: String) {
        template.convertAndSend(topic.topic, message)
    }
}