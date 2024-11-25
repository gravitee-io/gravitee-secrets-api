package io.gravitee.secrets.api.discovery;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author Benoit BORDIGONI (benoit.bordigoni at graviteesource.com)
 * @author GraviteeSource Team
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SecretRefsLocationTest {

    @ParameterizedTest
    @CsvSource(value = { "null, null", "foo, null", "foo, ''", "foo, ' '", "null, bar", "'', bar", "' ', bar" }, nullValues = "null")
    void should_not_create_location(String kind, String id) {
        assertThatCode(() -> new SecretRefsLocation(kind, id)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void should_create_location() {
        assertThatCode(() -> new SecretRefsLocation("foo", "bar")).doesNotThrowAnyException();
    }
}
