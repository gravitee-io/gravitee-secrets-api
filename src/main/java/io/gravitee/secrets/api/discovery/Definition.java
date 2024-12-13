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
package io.gravitee.secrets.api.discovery;

import javax.annotation.Nonnull;
import org.springframework.util.StringUtils;

/**
 * Represent a definition (APIs, Configuration etc.) that can contain as secret
 * @param kind arbitrary unique kind of definition (v4-api, v2-api, dictionary-configuration) this is conventional as set in different products.
 * @param id the unique identifier of the definition as a String
 * @author Benoit BORDIGONI (benoit.bordigoni at graviteesource.com)
 * @author GraviteeSource Team
 */
public record Definition(@Nonnull String kind, @Nonnull String id) {
    public Definition {
        boolean ok = StringUtils.hasText(kind) && StringUtils.hasText(id);
        if (!ok) {
            throw new IllegalArgumentException("definition must have kind and id");
        }
    }
}
