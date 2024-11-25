package io.gravitee.secrets.api.spec;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

/**
 * @author Benoit BORDIGONI (benoit.bordigoni at graviteesource.com)
 * @author GraviteeSource Team
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ACLsTest {

    @Test
    void should_create_ok_plugin_ACL() {
        Assertions.assertThatCode(() -> new ACLs.PluginACL("foo", null)).doesNotThrowAnyException();
        Assertions.assertThatCode(() -> new ACLs.PluginACL("foo", List.of())).doesNotThrowAnyException();
        Assertions.assertThatCode(() -> new ACLs.PluginACL("foo", List.of("bar"))).doesNotThrowAnyException();
    }

    @Test
    void should_create_nok_plugin_ACL() {
        Assertions.assertThatCode(() -> new ACLs.PluginACL(null, null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void should_create_ok_def_ACL() {
        Assertions.assertThatCode(() -> new ACLs.DefinitionACL("foo", null)).doesNotThrowAnyException();
        Assertions.assertThatCode(() -> new ACLs.DefinitionACL("foo", List.of())).doesNotThrowAnyException();
        Assertions.assertThatCode(() -> new ACLs.DefinitionACL("foo", List.of("bar"))).doesNotThrowAnyException();
    }

    @Test
    void should_create_nok_def_ACL() {
        Assertions.assertThatCode(() -> new ACLs.DefinitionACL(null, null)).isInstanceOf(IllegalArgumentException.class);
    }
}
