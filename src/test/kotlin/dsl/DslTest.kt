package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {

    @ValueSource(strings = ["홍길동", "정진혁"])
    @ParameterizedTest
    fun name(name: String) {
        val person = introduce {
            name(name)
        }

        assertThat(person.name).isEqualTo(name)
    }

    @Test
    fun company() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
        }

        assertThat(person.name).isEqualTo("홍길동")
        assertThat(person.company).isEqualTo("활빈당")
    }
}
