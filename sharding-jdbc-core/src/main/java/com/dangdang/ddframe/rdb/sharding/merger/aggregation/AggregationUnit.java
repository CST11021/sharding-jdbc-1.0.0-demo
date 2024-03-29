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

package com.dangdang.ddframe.rdb.sharding.merger.aggregation;

import com.dangdang.ddframe.rdb.sharding.merger.common.ResultSetQueryIndex;
import com.dangdang.ddframe.rdb.sharding.parser.result.merger.AggregationColumn;

import java.sql.SQLException;

/**
 * 归并计算单元接口.
 * 
 * @author gaohongtao
 */
public interface AggregationUnit {
    
    /**
     * 归并聚合值.
     * 
     * @param aggregationColumn     聚合列
     * @param aggregationValue      聚合值
     * @param resultSetQueryIndex   结果集查询索引
     * @throws SQLException
     */
    void merge(AggregationColumn aggregationColumn, AggregationValue aggregationValue, ResultSetQueryIndex resultSetQueryIndex) throws SQLException;
    
    /**
     * 获取计算结果.
     * 
     * @return 计算结果
     */
    Comparable<?> getResult();
}
