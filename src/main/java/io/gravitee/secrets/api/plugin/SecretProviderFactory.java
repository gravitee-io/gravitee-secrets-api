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
package io.gravitee.secrets.api.plugin;

/**
 * This is the plugin class for plugins of type "secret-provider".
 *
 * @author Benoit BORDIGONI (benoit.bordigoni at graviteesource.com)
 * @author GraviteeSource Team
 */
public interface SecretProviderFactory<T extends SecretManagerConfiguration> {
    /**
     * Creates a new instance of a {@link SecretProvider}
     *
     * @param configuration the configuration object
     * @return a secret provider if the configuration can be consumed.
     */
    SecretProvider create(T configuration);
}
