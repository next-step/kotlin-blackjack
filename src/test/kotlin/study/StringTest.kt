package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StringTest {

    @Test
    fun `sut returns lastChar`() {
        assertThat("Kotlin".lastChar()).isEqualTo('n')
    }
}
