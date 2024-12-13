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

import java.util.function.Consumer;

/**
 * Define interaction between {@link DefinitionSecretRefsFinder} and the discovery process
 * @author Benoit BORDIGONI (benoit.bordigoni at graviteesource.com)
 * @author GraviteeSource Team
 */
@FunctionalInterface
public interface DefinitionSecretRefsListener {
    /**
     * Is called whenever a {@link DefinitionSecretRefsFinder} find a location (e.g. plugin configuration)
     * to find secret refs into to do the following:
     * <ul>
     * <li>finds secret references (if any)</li>
     * <li>parse ref</li>
     * <li>register a ref was found (a.k.a discovery context)</li>
     * <li>replace with an EL expression that actually fetches the secret at runtime</li>
     * </ul>
     * @param candidateSecretRefsHolder the string candidate to contain the secret reference
     * @param location where this candidate was found
     * @param updatedSecretRefsHolder a lambda-expression to return processed candidate
     */
    void onCandidate(String candidateSecretRefsHolder, SecretRefsLocation location, Consumer<String> updatedSecretRefsHolder);
}
