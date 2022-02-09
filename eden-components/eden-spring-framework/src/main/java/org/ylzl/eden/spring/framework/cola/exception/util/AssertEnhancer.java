/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ylzl.eden.spring.framework.cola.exception.util;

import lombok.NonNull;
import org.springframework.util.Assert;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Map;

/**
 * 断言增强版
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.x
 */
public class AssertEnhancer extends Assert {

	public static void doesNotContain(@NonNull String textToSearch, String substring, String message,
									  Object... placeholders) {
		doesNotContain(textToSearch, substring, MessageFormat.format(message, placeholders));
	}

	public static void hasLength(@NonNull String expression, String message, Object... placeholders) {
		hasLength(expression, MessageFormat.format(message, placeholders));
	}

	public static void hasText(String text, String message, Object... placeholders) {
		hasText(text, MessageFormat.format(message, placeholders));
	}

	public static void isInstanceOf(Class<?> type, @NonNull Object obj, String message, Object... placeholders) {
		isInstanceOf(type, obj, MessageFormat.format(message, placeholders));
	}

	public static void isNull(Object object, String message, Object... placeholders) {
		isNull(object, MessageFormat.format(message, placeholders));
	}

	public static void notNull(Object object, String message, Object... placeholders) {
		notNull(object, MessageFormat.format(message, placeholders));
	}

	public static void isTrue(boolean expression, String message, Object... placeholders) {
		isTrue(expression, MessageFormat.format(message, placeholders));
	}

	public static void noNullElements(@NonNull Collection<?> collection, String message, Object... placeholders) {
		noNullElements(collection, MessageFormat.format(message, placeholders));
	}

	public static void notEmpty(@NonNull Object[] array, String message, Object... placeholders) {
		notEmpty(array, MessageFormat.format(message, placeholders));
	}

	public static void notEmpty(@NonNull Collection<?> collection, String message, Object... placeholders) {
		notEmpty(collection, MessageFormat.format(message, placeholders));
	}

	public static void notEmpty(@NonNull Map<?, ?> map, String message, Object... placeholders) {
		notEmpty(map, MessageFormat.format(message, placeholders));
	}

	public static void isAssignable(Class<?> superType, @NonNull Class<?> subType, String message, Object... placeholders) {
		isAssignable(superType, subType, MessageFormat.format(message, placeholders));
	}

	public static void state(boolean expression, String message, Object... placeholders) {
		state(expression, MessageFormat.format(message, placeholders));
	}
}
