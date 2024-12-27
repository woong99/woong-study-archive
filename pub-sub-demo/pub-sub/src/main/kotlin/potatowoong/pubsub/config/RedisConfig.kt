package potatowoong.pubsub.config

import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.StringRedisSerializer
import potatowoong.pubsub.listener.RedisTicketEventListener

@Configuration
@EnableRedisRepositories
class RedisConfig(
    private val redisProperties: RedisProperties,
    private val redisTicketEventListener: RedisTicketEventListener,
) {

    /**
     * Lettuce Connection Factory 설정
     *
     */
    @Bean
    fun redisConnectionFactory() = LettuceConnectionFactory(
        redisProperties.host,
        redisProperties.port
    )

    /**
     * RedisTemplate 설정
     */
    @Bean
    fun redisTemplate(): RedisTemplate<*, *> {
        return RedisTemplate<Any, Any>().apply {
            this.connectionFactory = redisConnectionFactory()
            this.keySerializer = StringRedisSerializer()
            this.valueSerializer = StringRedisSerializer()
        }
    }

    /**
     * Redis Pub/Sub을 사용하기 위한 설정
     */
    @Bean
    fun redisMessageListener(): RedisMessageListenerContainer {
        return RedisMessageListenerContainer().apply {
            this.setConnectionFactory(redisConnectionFactory())
            this.addMessageListener(redisTicketEventListener, ChannelTopic.of("ticket"))
        }
    }
}