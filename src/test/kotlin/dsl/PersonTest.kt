package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PersonTest {

    @Test
    fun `Person 객체를 생성한다`() {
        val person = Person()
        assertThat(person).isNotNull
    }

    @Test
    fun `Person은 name 정보를 갖으며 DSL로 생성한다`() {
        val person: Person = introduce {
            name = "김승갑"
        }

        assertThat(person.name).isEqualTo("김승갑")
    }
}
