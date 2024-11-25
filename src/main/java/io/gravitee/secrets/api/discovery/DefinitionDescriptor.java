package io.gravitee.secrets.api.discovery;

import java.util.Optional;

/**
 * Represent a definition (APIs, Configuration etc.) that can contain as secret
 * @param revision optionally the revision of the definition
 * @author Benoit BORDIGONI (benoit.bordigoni at graviteesource.com)
 * @author GraviteeSource Team
 */
public record DefinitionDescriptor(Definition definition, Optional<String> revision) {
    public DefinitionDescriptor {
        if (definition == null) {
            throw new IllegalArgumentException("definition is mandatory");
        }
    }
}
