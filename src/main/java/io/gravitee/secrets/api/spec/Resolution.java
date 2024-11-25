package io.gravitee.secrets.api.spec;

import java.time.Duration;

/**
 * Defines how the resolution is performed
 * @param type type of resolution
 * @param duration poll interval when used with {@link Resolution.Type#POLL}
 *                 time-to-live when used with {@link Resolution.Type#TTL}
 * @author Benoit BORDIGONI (benoit.bordigoni at graviteesource.com)
 * @author GraviteeSource Team
 */
public record Resolution(Type type, Duration duration) {
    public Resolution {
        boolean ok = type == Type.ONCE || duration != null && duration.toMillis() > 0;
        if (!ok) {
            throw new IllegalArgumentException("resolution lacks positive duration for type %s".formatted(type));
        }
    }

    public enum Type {
        /**
         * The secret value is fetch only once
         */
        ONCE,
        /**
         * The secret value is fetch regularly following an interval defined by  {@link Resolution#duration()}
         */
        POLL,
        /**
         * The secret value is fetch once,
         * then when pulling the value from the cache, if the secret expiration exceeds the time-to-live defined in {@link Resolution#duration()}
         * then it will be fetched again in the background
         */
        TTL,
    }
}
