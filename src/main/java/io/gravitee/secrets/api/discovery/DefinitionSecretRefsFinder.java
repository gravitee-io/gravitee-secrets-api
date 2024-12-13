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

/**
 * Finds locations (fields/configurations) that can contain secret references and notifies a {@link DefinitionSecretRefsListener}
 * @param <T> the type of definition
 * @author Benoit BORDIGONI (benoit.bordigoni at graviteesource.com)
 * @author GraviteeSource Team
 */
public interface DefinitionSecretRefsFinder<T> {
    /**
     * Compute if the <code>definition</code> passed can be processed
     * @param definition the definition to test
     * @return true if it can handle the definition
     */
    boolean canHandle(Object definition);

    /**
     * Return a description of the definition to be used when checking ACLs
     *
     * @param definition the definition object to analyse
     * @param metadata   additional properties related to the definition
     * @return a descriptor of the definition
     */
    DefinitionDescriptor toDefinitionDescriptor(T definition, DefinitionMetadata metadata);

    /**
     * This method searches in <code>definition</code> for location (fields/configurations) possibly containing secret refs
     * @param definition the definition object to process
     * @param listener the listener to notify when a candidate location is found
     */
    void findSecretRefs(T definition, DefinitionSecretRefsListener listener);
}
