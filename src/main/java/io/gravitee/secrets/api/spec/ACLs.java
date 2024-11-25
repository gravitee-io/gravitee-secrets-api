package io.gravitee.secrets.api.spec;

import io.gravitee.secrets.api.discovery.DefinitionDescriptor;
import io.gravitee.secrets.api.el.FieldKind;
import io.gravitee.secrets.api.el.RuntimeContext;
import java.util.List;
import org.springframework.util.StringUtils;

/**
 * <p>Defines constraints under which a secret defined by a {@link SecretSpec} can be resolved.</p>
 * <p>The policy is allow all by default hence when a field is null no constraint is applied</p>
 * @param fieldKind enforce the secret to be of the same kind as the field in the definition states (via ephemeral EL variable of type {@link RuntimeContext} ),
 *                  if the definition field does provide the kind then this constraint is not enforced.
 * @param definitions all definitions where the secret can used into
 * @param plugins all plugins where the secret can used into
 * @author Benoit BORDIGONI (benoit.bordigoni at graviteesource.com)
 * @author GraviteeSource Team
 * @see DefinitionACL
 * @see PluginACL
 * @see RuntimeContext
 */

public record ACLs(FieldKind fieldKind, List<DefinitionACL> definitions, List<PluginACL> plugins) {
    /**
     * Specifies which definition can contain a secret defined by a {@link SecretSpec}
     * @param kind the kind of definition as in {@link DefinitionDescriptor}
     * @param ids optional allowed ids for the kind, null or empty list means 'all'
     */
    public record DefinitionACL(String kind, List<String> ids) {
        public DefinitionACL {
            if (!StringUtils.hasText(kind)) {
                throw new IllegalArgumentException("definition kind is mandatory");
            }
            if (ids != null && ids.stream().anyMatch(String::isBlank)) {
                throw new IllegalArgumentException("definition ids cannot contain null or blank");
            }
        }
    }

    /**
     * Defines which plugins are allowed to resolve a secret defined by a {@link SecretSpec}
     * @param id the plugin id
     * @param fields optional allowed field names for that plugin, null or empty list means 'all'
     */
    public record PluginACL(String id, List<String> fields) {
        public PluginACL {
            if (!StringUtils.hasText(id)) {
                throw new IllegalArgumentException("plugin id is mandatory");
            }
        }
    }
}
