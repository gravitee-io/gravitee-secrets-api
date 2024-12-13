/*
 * Copyright © 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gravitee.secrets.api.spec;

import java.time.Duration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

/**
 * @author Benoit BORDIGONI (benoit.bordigoni at graviteesource.com)
 * @author GraviteeSource Team
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ResolutionTest {

    @Test
    void should_create_ok_resolution() {
        Assertions.assertThatCode(() -> new Resolution(Resolution.Type.ONCE, null)).doesNotThrowAnyException();
        Assertions.assertThatCode(() -> new Resolution(Resolution.Type.ONCE, Duration.ZERO)).doesNotThrowAnyException();
        Assertions.assertThatCode(() -> new Resolution(Resolution.Type.POLL, Duration.ofSeconds(1))).doesNotThrowAnyException();
        Assertions.assertThatCode(() -> new Resolution(Resolution.Type.TTL, Duration.ofSeconds(1))).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @EnumSource(value = Resolution.Type.class, names = { "ONCE" }, mode = EnumSource.Mode.EXCLUDE)
    void should_create_nok_resolution(Resolution.Type type) {
        Assertions.assertThatCode(() -> new Resolution(type, null)).isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatCode(() -> new Resolution(type, Duration.ZERO)).isInstanceOf(IllegalArgumentException.class);
        Duration negativeDuration = Duration.ZERO.minusMillis(1);
        Assertions.assertThatCode(() -> new Resolution(type, negativeDuration)).isInstanceOf(IllegalArgumentException.class);
    }
}
