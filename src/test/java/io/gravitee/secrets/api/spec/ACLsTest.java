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

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

/**
 * @author Benoit BORDIGONI (benoit.bordigoni at graviteesource.com)
 * @author GraviteeSource Team
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ACLsTest {

    @Test
    void should_create_ok_plugin_ACL() {
        Assertions.assertThatCode(() -> new ACLs.PluginACL("foo", null)).doesNotThrowAnyException();
        Assertions.assertThatCode(() -> new ACLs.PluginACL("foo", List.of())).doesNotThrowAnyException();
        Assertions.assertThatCode(() -> new ACLs.PluginACL("foo", List.of("bar"))).doesNotThrowAnyException();
    }

    @Test
    void should_create_nok_plugin_ACL() {
        Assertions.assertThatCode(() -> new ACLs.PluginACL(null, null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void should_create_ok_def_ACL() {
        Assertions.assertThatCode(() -> new ACLs.DefinitionACL("foo", null)).doesNotThrowAnyException();
        Assertions.assertThatCode(() -> new ACLs.DefinitionACL("foo", List.of())).doesNotThrowAnyException();
        Assertions.assertThatCode(() -> new ACLs.DefinitionACL("foo", List.of("bar"))).doesNotThrowAnyException();
    }

    @Test
    void should_create_nok_def_ACL() {
        Assertions.assertThatCode(() -> new ACLs.DefinitionACL(null, null)).isInstanceOf(IllegalArgumentException.class);
    }
}
