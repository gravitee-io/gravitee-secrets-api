package io.gravitee.secrets.api.el;

import io.gravitee.secrets.api.spec.SecretSpec;
import java.util.List;

/**
 * Define methods that are called as an EL to resolve secrets.
 * @author Benoit BORDIGONI (benoit.bordigoni at graviteesource.com)
 * @author GraviteeSource Team
 */
public interface EvaluatedSecretsMethods {
    /**
     * Pulls an already resolved secret from the cache only if has been is granted to be used in the context it was discovered into.
     * The discovery context id used a token to check the secret was granted.
     * @param contextId discovery context ID
     * @param secretFieldAccessControl caller of this EL may pass this to describe the field to access control can be performed.
     * @return the secret or an empty string depending on {@link SecretSpec#onErrorStrategy()}
     */
    String fromGrant(String contextId, SecretFieldAccessControl secretFieldAccessControl);

    /**
     * Pulls an already resolved secret from the cache only if has been is granted to be used in the context it was discovered into.
     * The discovery context id used a token to check the secret was granted. This method is called when the secret key is not known in advance (EL).
     * @param contextId discovery context ID
     * @param secretKey the secret key value (EL evaluated)
     * @param secretFieldAccessControl caller of this EL may pass this to describe the field to access control can be performed.
     * @return the secret or an empty string depending on {@link SecretSpec#onErrorStrategy()}
     */
    String fromGrant(String contextId, String secretKey, SecretFieldAccessControl secretFieldAccessControl);

    /**
     * Finds or generate (for URIs) a spec from <code>uriOrName</code>.
     * Then using <code>envId</code> <code>definitionKind</code>
     * <code>definitionId</code>  <code>locations</code>
     * creates a temporary discovery context and checks if it is granted
     * @param envId environment ID
     * @param uriOrName evaluated value of the EL set in the ref.
     *                  Starting with '/' it is considered a URI else a name
     * @param definitionKind kind of the definition where the ref was found
     * @param definitionId id of the definition where the ref was found
     * @param locations list of location index <code>i</code> is the type of location (e.g plugin) <code>i+1</code> is the id of the location (e.g plugin id)
     * @param secretFieldAccessControl caller of this EL may pass this to describe the field to access control can be performed.
     * @return the secret or an empty string depending on {@link SecretSpec#onErrorStrategy()}
     */
    String fromEL(
        String envId,
        String uriOrName,
        String definitionKind,
        String definitionId,
        List<String> locations,
        SecretFieldAccessControl secretFieldAccessControl
    );
}
