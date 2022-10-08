package org.ylzl.eden.mq.adapter.autoconfigure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.mq.adapter.core.MessageQueueProviderFactory;
import org.ylzl.eden.mq.adapter.env.MessageQueueProperties;

/**
 * 分布式锁操作自动装配
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@ConditionalOnProperty(name = MessageQueueProperties.ENABLED, matchIfMissing = true)
@EnableConfigurationProperties(MessageQueueProperties.class)
@RequiredArgsConstructor
@Slf4j
@Configuration(proxyBeanMethods = false)
public class MessageQueueAutoConfiguration {

	public static final String AUTOWIRED_MESSAGE_QUEUE_FACTORY = "Autowired MessageQueueProviderFactory";

	private final MessageQueueProperties messageQueueProperties;

	@Bean
	public MessageQueueProviderFactory messageQueueProviderFactory() {
		log.debug(AUTOWIRED_MESSAGE_QUEUE_FACTORY);
		return new MessageQueueProviderFactory(messageQueueProperties.getType());
	}
}
