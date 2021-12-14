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

package me.ahoo.cosid.shardingsphere.sharding.interval;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * @author ahoo wang
 */
public final class LocalDateTimeConvert {

    public static LocalDateTime fromDate(Date date, ZoneId zoneId) {
        return LocalDateTime.ofInstant(date.toInstant(), zoneId);
    }

    public static LocalDateTime fromTimestamp(Long timestamp, ZoneId zoneId) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), zoneId);
    }

    public static LocalDateTime fromTimestampSecond(Long timestamp, ZoneId zoneId) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), zoneId);
    }

    public static LocalDateTime fromString(String dateTime, DateTimeFormatter dateTimeFormatter) {
        TemporalAccessor temporalAccessor = dateTimeFormatter.parseBest(dateTime, LocalDateTime::from, LocalDate::from);
        if (temporalAccessor instanceof LocalDateTime) {
            return (LocalDateTime) temporalAccessor;
        }
        return LocalDateTime.of((LocalDate) temporalAccessor, LocalTime.MIN);
    }
}