package fp

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class LamdaTest {
    val numbers = listOf(1, 2, 3, 4, 5, 6)

    @Test
    fun `모두 더하기`() {
        Assertions.assertThat(sumAll(numbers)).isEqualTo(numbers.sum())
    }

    @Test
    fun `짝수 더하기`() {
        Assertions.assertThat(sumAllEven(numbers)).isEqualTo(numbers.asSequence().filter { it % 2 == 0 }.sum())
    }

    @Test
    fun `3이상 모두 더하기`() {
        Assertions.assertThat(sumAllOverThree(numbers)).isEqualTo(numbers.asSequence().filter { it > 3 }.sum())
    }
}
