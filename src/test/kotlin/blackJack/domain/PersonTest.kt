package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PersonTest {
    @Test
    fun makePerson() {
        val person = Person("joohan")

        assertThat(person.hands).hasSize(0)
        assertThat(person.name).isEqualTo("joohan")
    }
}
