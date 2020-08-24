package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class PersonTest {
    @Test
    fun makePerson() {
        val person = Person("joohan")

        assertThat(person.hands).hasSize(0)
        assertThat(person.name).isEqualTo("joohan")
    }

    @Test
    fun if_name_is_blank() {
        assertThatThrownBy {
            Person("")
        }.isInstanceOf(IllegalArgumentException::class.java).hasMessageContaining("이름은 없을수 없습니다.")
    }
}
