package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InfixNotationTest {

    @Test
    fun `sut returns infix to`() {
        val pair = 1 to "one"
        assertThat(pair.first).isEqualTo(1)
        assertThat(pair.second).isEqualTo("one")
    }
}
