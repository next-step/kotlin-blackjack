package fp

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LambdaTest {

    @Test
    fun `모든 수의 총합`() {
        val actual = 15
        val lambda = Lambda.sumAll(listOf(1, 2, 3, 4, 5))
        assertThat(actual).isEqualTo(lambda)
    }

    @Test
    fun `모든 짝의 총합`() {
        val actual = 6
        val lambda = Lambda.sumAllEven(listOf(1, 2, 3, 4, 5))
        assertThat(actual).isEqualTo(lambda)
    }

    @Test
    fun `3보다 큰수 총합`() {
        val actual = 9
        val lambda = Lambda.sumAllOverThree(listOf(1, 2, 3, 4, 5))
        assertThat(actual).isEqualTo(lambda)
    }
}
