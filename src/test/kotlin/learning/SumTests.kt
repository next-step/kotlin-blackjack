package learning

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class SumTests {
    private val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

    @Test
    fun `숫자들을 더한다`() {
        assertAll(
            { assertThat(sumAll(numbers)).isEqualTo(1 + 2 + 3 + 4 + 5 + 6) },
            { assertThat(sumAllEven(numbers)).isEqualTo(2 + 4 + 6) },
            { assertThat(sumAllOverThree(numbers)).isEqualTo(4 + 5 + 6) },
        )
    }

    private fun sumAll(numbers: List<Int>): Int = numbers
        .sum()

    private fun sumAllEven(numbers: List<Int>): Int = numbers
        .filter { it % 2 == 0 }
        .sum()

    private fun sumAllOverThree(numbers: List<Int>): Int = numbers
        .filter { it > 3 }
        .sum()
}
