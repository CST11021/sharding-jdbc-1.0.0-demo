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

package com.dangdang.ddframe.rdb.sharding.router.single;

import com.dangdang.ddframe.rdb.sharding.parser.result.router.SQLBuilder;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * 单表路由表单元.
 * 
 * @author gaohongtao
 */
@RequiredArgsConstructor
@Getter
@ToString
public class SingleRoutingTableFactor {

    /** 逻辑表的表名 */
    private final String logicTable;
    /** 真实表的表名 */
    private final String actualTable;
    
    /**
     * 构建SQL.
     * 
     * @param builder SQL构建器
     */
    public void buildSQL(final SQLBuilder builder) {
        builder.buildSQL(logicTable, actualTable);
    }
}
