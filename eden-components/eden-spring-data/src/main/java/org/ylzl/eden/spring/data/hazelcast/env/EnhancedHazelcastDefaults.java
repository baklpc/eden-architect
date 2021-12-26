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

package org.ylzl.eden.spring.data.hazelcast.env;

import lombok.experimental.UtilityClass;
import org.ylzl.eden.commons.lang.StringConstants;

/**
 * Hazelcast 配置属性默认值
 *
 * @author gyl
 * @since 1.0.0
 */
@UtilityClass
public final class EnhancedHazelcastDefaults {

  public static final int backupCount = 1;

  public static final int timeToLiveSeconds = 3600;

  @UtilityClass
  public static class ManagementCenter {

    public static final boolean enabled = false;

    public static final int updateInterval = 3;

    public static final String url = StringConstants.EMPTY;
  }
}