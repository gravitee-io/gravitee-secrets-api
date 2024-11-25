package io.gravitee.secrets.api.spec;

/**
 * @author Benoit BORDIGONI (benoit.bordigoni at graviteesource.com)
 * @author GraviteeSource Team
 */
public record OnErrorStrategy(boolean raiseError, ReturnEmpty returnEmpty) {
    public record ReturnEmpty(boolean ifNotFound, boolean ifEmpty, boolean ifDenied, boolean ifError) {}
}
