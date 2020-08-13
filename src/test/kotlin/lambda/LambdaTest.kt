package lambda

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LambdaTest {
    private val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

    @Test
    fun sum_all() {
        val result = sum(numbers) { true }

        assertThat(result).isEqualTo(21)
    }

    @Test
    fun sum_all_even() {
        val result = sum(numbers) { it % 2 == 0 }

        assertThat(result).isEqualTo(12)
    }

    @Test
    fun sum_all_over_three() {
        val result = sum(numbers) { it > 3 }

        assertThat(result).isEqualTo(15)
    }
}
