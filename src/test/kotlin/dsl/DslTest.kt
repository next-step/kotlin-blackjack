package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["김호준", "쿠키"])
    @ParameterizedTest
    fun introduce(name: String) {
        val person = Person(name)
        assertThat(person.name).isEqualTo(name)
    }
}
