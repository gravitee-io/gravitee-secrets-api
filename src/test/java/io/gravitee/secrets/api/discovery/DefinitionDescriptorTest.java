package io.gravitee.secrets.api.discovery;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.Optional;
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
class DefinitionDescriptorTest {

    @CsvSource(value = { "null, null", "foo, null", "foo, ''", "foo, ' '", "null, bar", "'', bar", "' ', bar" }, nullValues = "null")
    @ParameterizedTest
    void should_not_create_descriptor(String kind, String id) {
        assertThatCode(() -> new Definition(kind, id)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void should_create_descriptor() {
        Definition definition = new Definition("foo", "bar");
        assertThatCode(() -> new DefinitionDescriptor(definition, Optional.empty())).doesNotThrowAnyException();
        assertThatCode(() -> new DefinitionDescriptor(definition, Optional.of("1"))).doesNotThrowAnyException();
    }
}
