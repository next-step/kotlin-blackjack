package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class PersonTest {
    @ParameterizedTest
    @ValueSource(strings = ["김승갑", "justin"])
    fun `Person은 name 정보를 갖으며 DSL로 생성한다`(name: String) {
        val person: Person = introduce {
            name(name)
        }

        assertThat(person.name).isEqualTo(name)
    }
}
