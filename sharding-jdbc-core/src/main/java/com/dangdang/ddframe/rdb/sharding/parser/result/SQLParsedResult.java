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

package com.dangdang.ddframe.rdb.sharding.parser.result;

import com.dangdang.ddframe.rdb.sharding.parser.result.merger.MergeContext;
import com.dangdang.ddframe.rdb.sharding.parser.result.router.ConditionContext;
import com.dangdang.ddframe.rdb.sharding.parser.result.router.RouteContext;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * SQL解析结果.
 * 
 * @author gaohongtao, zhangliang
 */
@Getter
@ToString
public final class SQLParsedResult {

    /** 路由上下文（表和SQL） */
    private final RouteContext routeContext = new RouteContext();

    /** 条件上下文（条件字段） */
    private final List<ConditionContext> conditionContexts = new ArrayList<>();

    /** 结果merge上下文 */
    private final MergeContext mergeContext = new MergeContext();

}
