/*
 * Copyright [2021-2021] [ahoo wang <ahoowang@qq.com> (https://github.com/Ahoo-Wang)].
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.ahoo.cosid.converter;

import me.ahoo.cosid.IdConverter;
import me.ahoo.cosid.snowflake.SnowflakeIdStateParser;

/**
 * @author ahoo wang
 */
public class SnowflakeFriendlyIdConverter implements IdConverter {
    private final SnowflakeIdStateParser snowflakeIdStateParser;

    public SnowflakeFriendlyIdConverter(SnowflakeIdStateParser snowflakeIdStateParser) {
        this.snowflakeIdStateParser = snowflakeIdStateParser;
    }

    public SnowflakeIdStateParser getParser() {
        return snowflakeIdStateParser;
    }

    @Override
    public String asString(long id) {
        return snowflakeIdStateParser.parse(id).getFriendlyId();
    }

    @Override
    public long asLong(String idString) {
        return snowflakeIdStateParser.parse(idString).getId();
    }
}
