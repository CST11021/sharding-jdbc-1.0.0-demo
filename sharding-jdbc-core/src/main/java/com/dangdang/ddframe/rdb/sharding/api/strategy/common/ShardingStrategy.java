/**
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.dangdang.ddframe.rdb.sharding.api.strategy.common;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collection;

/**
 * 分片策略.
 * 
 * @author zhangliang
 */
@RequiredArgsConstructor
public class ShardingStrategy {

    /** 分片字段，sharding支持根据多个字段进行分片 */
    @Getter
    private final Collection<String> shardingColumns;

    /** 分片实现 */
    private final ShardingAlgorithm shardingAlgorithm;
    
    public ShardingStrategy(final String shardingColumn, final ShardingAlgorithm shardingAlgorithm) {
        this(Arrays.asList(shardingColumn), shardingAlgorithm);
    }
    
    /**
     * 根据分片值计算数据源名称集合.
     * 
     * @param availableTargetNames  所有的可用数据源名称集合
     * @param shardingValues        分库片值集合
     * @return 分库后指向的数据源名称集合
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Collection<String> doSharding(final Collection<String> availableTargetNames, final Collection<ShardingValue<? extends Comparable<?>>> shardingValues) {
        if (shardingValues.isEmpty()) {
            return availableTargetNames;
        }

        // 单字段分票
        if (shardingAlgorithm instanceof SingleKeyShardingAlgorithm) {
            SingleKeyShardingAlgorithm<?> singleKeyShardingAlgorithm = (SingleKeyShardingAlgorithm<?>) shardingAlgorithm;
            ShardingValue shardingValue = shardingValues.iterator().next();
            switch (shardingValue.getType()) {
                case SINGLE: 
                    return Arrays.asList(singleKeyShardingAlgorithm.doEqualSharding(availableTargetNames, shardingValue));
                case LIST: 
                    return singleKeyShardingAlgorithm.doInSharding(availableTargetNames, shardingValue);
                case RANGE: 
                    return singleKeyShardingAlgorithm.doBetweenSharding(availableTargetNames, shardingValue);
                default: 
                    throw new UnsupportedOperationException(shardingValue.getType().getClass().getName());
            }
        }

        // 多字段分片
        if (shardingAlgorithm instanceof MultipleKeysShardingAlgorithm) {
            return ((MultipleKeysShardingAlgorithm) shardingAlgorithm).doSharding(availableTargetNames, shardingValues);
        }

        throw new UnsupportedOperationException(shardingAlgorithm.getClass().getName());
    }
}
