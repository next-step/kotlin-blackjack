package fp

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LambdaTest {
    private val lambda = Lambda()
    private val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

    @Test
    fun sumAll() {
        assertThat(lambda.sumAll(numbers)).isEqualTo(21)
    }

    @Test
    fun sumAllEven() {
        assertThat(lambda.sumAllEven(numbers)).isEqualTo(12)
    }

    @Test
    fun sumAllOverThree() {
        assertThat(lambda.sumAllOverThree(numbers)).isEqualTo(15)
    }
}
