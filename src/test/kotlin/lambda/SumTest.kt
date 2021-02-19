package lambda

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SumTest {
    val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

    @Test
    internal fun sumAll() {
        assertThat(Sum.sumAll(numbers)).isEqualTo(21)
    }

    @Test
    internal fun sumAllEven() {
        assertThat(Sum.sumAllEven(numbers)).isEqualTo(12)
    }

    @Test
    internal fun sumAllOverThree() {
        assertThat(Sum.sumAllOverThree(numbers)).isEqualTo(15)
    }
}
