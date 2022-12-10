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

package org.ylzl.eden.dynamic.cache.spring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.ylzl.eden.dynamic.cache.builder.CacheBuilder;
import org.ylzl.eden.dynamic.cache.config.CacheConfig;
import org.ylzl.eden.dynamic.cache.expire.CacheExpiredListener;
import org.ylzl.eden.dynamic.cache.factory.CacheFactory;
import org.ylzl.eden.dynamic.cache.sync.CacheSynchronizer;
import org.ylzl.eden.extension.ExtensionLoader;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Spring 缓存管理器
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.x
 */
@RequiredArgsConstructor
@Slf4j
public class SpringCacheManager implements CacheManager {

	private final ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<>(16);

	private final CacheConfig config;

	private final CacheSynchronizer synchronizer;

	private final CacheExpiredListener<Object, Object> listener;

	private final Object cacheClient;

	@Override
	public Cache getCache(@NotNull String name) {
		Cache cache = this.cacheMap.get(name);
		if (cache == null && this.config.isDynamic()) {
			synchronized (this.cacheMap) {
				cache = this.cacheMap.computeIfAbsent(name, n -> this.createSpringCache(config.getCacheType(), n));
			}
		}
		return cache;
	}

	@Override
	public @NotNull Collection<String> getCacheNames() {
		return Collections.unmodifiableSet(this.cacheMap.keySet());
	}

	private Cache createSpringCache(String cacheType, String cacheName) {
		org.ylzl.eden.dynamic.cache.Cache cache = getOrCreateCache(cacheType, cacheName);
		return new SpringCache(this.config.isAllowNullValues(), cacheName, cache);
	}

	private org.ylzl.eden.dynamic.cache.Cache getOrCreateCache(String cacheType, String cacheName) {
		org.ylzl.eden.dynamic.cache.Cache cache = CacheFactory.getCache(cacheType, cacheName);
		if (null != cache) {
			return cache;
		}

		CacheBuilder<?> cacheBuilder = ExtensionLoader.getExtensionLoader(CacheBuilder.class).getExtension(cacheType);
		cacheBuilder.setCacheConfig(this.config)
			.setCacheSynchronizer(this.synchronizer)
			.setExpiredListener(listener)
			.setCacheClient(this.cacheClient);
		return CacheFactory.getOrCreateCache(cacheType, cacheName, cacheBuilder);
	}
}