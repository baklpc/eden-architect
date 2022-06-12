package org.ylzl.eden.spring.integration.businessprocess.autoconfigure;

import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 开启业务流程引擎
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.x
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({ProcessFactoryAutoConfiguration.class, ProcessContextFactoryRegistrar.class})
public @interface EnableBusinessProcessEngine {

	/**
	 * 配置文件
	 *
	 * @return
	 */
	@AliasFor("configPath")
	String[] value();

	/**
	 * 配置文件
	 *
	 * @return
	 */
	@AliasFor("value")
	String configPath();
}
