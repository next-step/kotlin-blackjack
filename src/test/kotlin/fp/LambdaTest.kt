package fp

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LambdaTest {

    @Test
    fun `sumAllTest`() {
        assertThat(sumAll(numbers)).isEqualTo(21)
    }

    @Test
    fun `sumAllEvenTest`() {
        assertThat(sumAllEven(numbers)).isEqualTo(12)
    }

    @Test
    fun `sumAllOverThreeTest`() {
        assertThat(sumAllOverThree(numbers)).isEqualTo(15)
    }
}
