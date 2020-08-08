package lambda

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class LambdaKtTest {
    private val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

    @Test
    fun sumAll() {
        assertThat(numbers.sumAll()).isEqualTo(21)
    }

    @Test
    fun sumAllEven() {
        assertThat(numbers.sumAllEven()).isEqualTo(12)
    }

    @Test
    fun sumAllOverThree() {
        assertThat(numbers.sumAllOverThree()).isEqualTo(15)
    }
}
