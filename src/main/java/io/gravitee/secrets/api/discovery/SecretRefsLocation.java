package io.gravitee.secrets.api.discovery;

import org.springframework.util.StringUtils;

/**
 * Indicates where is located a string candidate to contain secret refs (plugin, mAPI configuration etc.)
 * @param kind a string to specify the kind of entity is holding the candidate string (e.g. plugin)
 * @param id a string to identify specifically the kind (e.g. plugin id)
 * @author Benoit BORDIGONI (benoit.bordigoni at graviteesource.com)
 * @author GraviteeSource Team
 */
public record SecretRefsLocation(String kind, String id) {
    public static final String PLUGIN_KIND = "plugin";
    public SecretRefsLocation {
        boolean ok = StringUtils.hasText(kind) && StringUtils.hasText(id);
        if (!ok) {
            throw new IllegalArgumentException("secret refs location must have a kind and an id");
        }
    }
    public static final SecretRefsLocation NOWHERE = new SecretRefsLocation("_", "_");
}
