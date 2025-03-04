/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ylzl.eden.spring.framework.logging.access.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.context.annotation.Role;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.ylzl.eden.spring.framework.logging.EnableAccessLog;
import org.ylzl.eden.spring.framework.logging.access.aop.AccessLogAdvisor;
import org.ylzl.eden.spring.framework.logging.access.aop.AccessLogInterceptor;

/**
 * 访问日志切面配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.x
 */
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@Slf4j
@Configuration(proxyBeanMethods = false)
public class AccessLogConfiguration implements ImportAware {

	private static final String IMPORTING_META_NOT_FOUND = "@EnableAccessLog is not present on importing class";

	private AnnotationAttributes annotation;

	@Override
	public void setImportMetadata(AnnotationMetadata importMetadata) {
		this.annotation = AnnotationAttributes.fromMap(
			importMetadata.getAnnotationAttributes(EnableAccessLog.class.getName(), false));
		if (this.annotation == null) {
			log.warn(IMPORTING_META_NOT_FOUND);
		}
	}

	@Bean
	public AccessLogAdvisor accessLogAdvisor(ObjectProvider<AccessLogConfig> configs,
														 AccessLogInterceptor interceptor) {
		AccessLogAdvisor advisor = new AccessLogAdvisor();
		String expression = getAccessLogConfig(configs).getExpression();
		advisor.setExpression(expression);
		advisor.setAdvice(interceptor);
		if (annotation != null) {
			advisor.setOrder(annotation.getNumber("order"));
		}
		return advisor;
	}

	@Bean
	public AccessLogInterceptor accessLogInterceptor(ObjectProvider<AccessLogConfig> configs) {
		return new AccessLogInterceptor(getAccessLogConfig(configs));
	}

	private AccessLogConfig getAccessLogConfig(ObjectProvider<AccessLogConfig> accessLogConfigs) {
		return accessLogConfigs.getIfUnique(() -> {
			AccessLogConfig config = new AccessLogConfig();
			config.setExpression(annotation.getString("expression"));
			return config;
		});
	}
}
