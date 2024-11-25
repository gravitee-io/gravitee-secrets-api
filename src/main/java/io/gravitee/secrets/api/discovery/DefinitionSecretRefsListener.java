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
