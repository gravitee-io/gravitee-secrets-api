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
package io.gravitee.secrets.api.core;

import static java.util.stream.Collectors.toMap;

import java.time.Instant;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.EqualsAndHashCode;

/**
 * Represent a secret in the Secret Manager. It is a map key/secret.
 * It can have an expiration to help cache eviction.
 * <p>
 * Secrets can be pulled directly or using {@link WellKnownSecretKey} for well known secrets type (TLS and Basic Auth).
 * An explicit call to {@link #handleWellKnownSecretKeys(Map)} with a mapping must be performed to extract well-known keys.
 *
 * @author Benoit BORDIGONI (benoit.bordigoni at graviteesource.com)
 * @author GraviteeSource Team
 */
@EqualsAndHashCode
public final class SecretMap implements WithExpiration {

    private final Map<String, Secret> map;
    private final Map<WellKnownSecretKey, Secret> wellKnown = new EnumMap<>(WellKnownSecretKey.class);
    private final Instant expiresAt;

    /**
     * Create a {@link SecretMap} from a map of {@link Secret} without expiration
     *
     * @param map the map of {@link Secret}
     */
    public SecretMap(Map<String, Secret> map) {
        this(map, null);
    }

    /**
     * Create a {@link SecretMap} from a map of {@link Secret} with expiration
     *
     * @param map      the map of {@link Secret}
     * @param expiresAt expiration
     */
    public SecretMap(Map<String, Secret> map, Instant expiresAt) {
        this.map = map == null ? Map.of() : Map.copyOf(map);
        this.expiresAt = expiresAt;
    }

    /**
     *
     * @return a copy of the secrets as immutable map
     */
    public Map<String, Secret> asMap() {
        return Map.copyOf(map);
    }

    /**
     * Builds a secret map where secrets are base64 encoded
     *
     * @param data the secret as a map (String/byte[] or String/String) where bytes or String are base64 encoded
     * @return a {@link SecretMap}
     * @see Secret#Secret(Object)
     */
    public static SecretMap ofBase64(Map<String, ?> data) {
        return new SecretMap(mapToMap(data, true));
    }

    /**
     * Builds a secret map where secrets are base64 encoded with expiration date
     *
     * @param data     the secret as a map (String/byte[] or String/String) where bytes or String are base64 encoded
     * @param expireAt when the secret expires
     * @return a {@link SecretMap}
     * @see Secret#Secret(Object)
     */
    public static SecretMap ofBase64(Map<String, ?> data, Instant expireAt) {
        return new SecretMap(mapToMap(data, true), expireAt);
    }

    /**
     * Builds a secret map
     *
     * @param data the secret as a map (String/byte[] or String/String)
     * @return a {@link SecretMap}
     * @see Secret#Secret(Object)
     */
    public static SecretMap of(Map<String, ?> data) {
        return new SecretMap(mapToMap(data, false));
    }

    /**
     * Builds a secret map with expiration date
     *
     * @param data     the secret as a map (String/byte[] or String/String)
     * @param expireAt when the secret expires
     * @return a {@link SecretMap}
     * @see Secret#Secret(Object)
     */
    public static SecretMap of(Map<String, ?> data, Instant expireAt) {
        return new SecretMap(mapToMap(data, false), expireAt);
    }

    private static Map<String, Secret> mapToMap(Map<String, ?> data, boolean base64) {
        return data.entrySet().stream().collect(toMap(Map.Entry::getKey, e -> new Secret(e.getValue(), base64)));
    }

    /**
     * Get a secret from the map using the {@link SecretURL#key()}
     *
     * @param secretURL the url to use
     * @return optional of a required secret
     */
    public Optional<Secret> getSecret(SecretURL secretURL) {
        return Optional.ofNullable(map.get(secretURL.key()));
    }

    /**
     * @return optional of the expiration of this secret
     */
    public Optional<Instant> expiresAt() {
        return Optional.ofNullable(expiresAt);
    }

    /**
     * Make well-know key accessible via {@link #wellKnown(WellKnownSecretKey)}
     * The map must be passed on as follows:
     * <li>key: the name of well-known key inside the secret data</li>
     * <li>value: the matching {@link WellKnownSecretKey}</li>
     * if the key in the secret is not found, it will be ignored
     *
     * @param mapping the map describing the mapping
     * @return this updated {@link SecretMap} instance
     */
    public SecretMap handleWellKnownSecretKeys(Map<String, WellKnownSecretKey> mapping) {
        map
            .entrySet()
            .stream()
            .filter(entry -> mapping.get(entry.getKey()) != null)
            .forEach(entry -> wellKnown.put(mapping.get(entry.getKey()), entry.getValue()));
        return this;
    }

    /**
     * Retrieve a well-known field in a secret as an option.
     *
     * @param key the key to return
     * @return optional of a secret.
     */
    public Optional<Secret> wellKnown(WellKnownSecretKey key) {
        return Optional.ofNullable(wellKnown.get(key));
    }

    /**
     * Compute a new secret map with expiration. If the <code>secretURL</code> has a key,
     * then only the secret matching that key will be set to expire. If not the whole map is set to expire.
     * @param secretURL the secret URL to fetch that secret
     * @param expireAt the expiration instant
     * @return a new {@link SecretMap} containing expiring secrets
     */
    public SecretMap withExpiresAt(SecretURL secretURL, Instant expireAt) {
        if (secretURL.key() == null || secretURL.key().isBlank()) {
            // the whole map can expire
            return new SecretMap(this.asMap(), expireAt);
        } else {
            Optional<Secret> expiring = this.getSecret(secretURL).map(secret -> secret.withExpiresAt(expireAt));
            // set the secret to expire
            if (expiring.isPresent()) {
                Map<String, Secret> secrets = new HashMap<>(this.asMap());
                secrets.put(secretURL.key(), expiring.get());
                return new SecretMap(secrets);
            }
            return this;
        }
    }

    /**
     * Well-known field that can typically exist find in a secret. This is from Gravitee.io point of view.
     * Any consumer of those field should use {@link SecretMap#wellKnown(WellKnownSecretKey)} to fetch the data.
     */
    public enum WellKnownSecretKey {
        CERTIFICATE,
        PRIVATE_KEY,
        USERNAME,
        PASSWORD,
    }
}
