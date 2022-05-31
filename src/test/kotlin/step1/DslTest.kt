package step1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ParameterizedTest
    @ValueSource(strings = ["배수정", "Vivian"])
    fun name(value: String) {
        val person = introduce {
            name(value)
        }

        assertThat(person.name).isEqualTo(value)
    }
}
