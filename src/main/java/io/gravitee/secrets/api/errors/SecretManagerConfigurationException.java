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
package io.gravitee.secrets.api.errors;

/**
 * Occurs when the configuration of a secret manager is badly formed
 * @author Benoit BORDIGONI (benoit.bordigoni at graviteesource.com)
 * @author GraviteeSource Team
 */
public class SecretManagerConfigurationException extends RuntimeException {

    public SecretManagerConfigurationException(String message) {
        super(message);
    }

    public SecretManagerConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecretManagerConfigurationException(Throwable cause) {
        super(cause);
    }
}
