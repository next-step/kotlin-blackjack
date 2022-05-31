package step1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @Test
    fun introduce() {
        val person = introduce { }

        assertThat(person.name).isEqualTo(PersonBuilder.DEFAULT_NAME)
    }

    @ParameterizedTest
    @ValueSource(strings = ["배수정", "Vivian"])
    fun name(value: String) {
        val person = introduce {
            name(value)
        }

        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val company = "카카오"

        val person = introduce {
            company(company)
        }

        assertThat(person.company).isEqualTo(company)
    }
}
