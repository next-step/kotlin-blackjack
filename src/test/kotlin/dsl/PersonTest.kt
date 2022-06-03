package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PersonTest {

    @Test
    fun `Person 객체를 생성한다`() {
        val person = Person()
        assertThat(person).isNotNull
    }
}
