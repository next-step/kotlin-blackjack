package dsl

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PersonTest {

    @Test
    fun `이름을 가진 사람을 만든다`() {
        val person: Person = introduce {
            name("김우재")
        }
        Assertions.assertThat(person.name).isEqualTo("김우재")
    }
}
