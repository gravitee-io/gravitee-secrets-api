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
