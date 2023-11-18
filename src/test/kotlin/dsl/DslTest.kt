package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["김호준", "쿠키"])
    @ParameterizedTest
    fun introduce(name: String) {
        val person = introduce {
            it.name = name
        }
        assertThat(person.name).isEqualTo(name)
    }

    @Test
    fun company() {
        val person = introduce {
            it.name = "김호준"
            it.company = "회사"
        }
        assertThat(person.name).isEqualTo("김호준")
        assertThat(person.company).isEqualTo("회사")
    }
}
