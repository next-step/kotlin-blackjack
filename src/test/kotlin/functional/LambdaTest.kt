package functional

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LambdaTest {

    @Test
    fun 총합구하기() {
        // given
        val lambda = Lambda()

        // then
        assertThat(lambda.sumAll(lambda.numbers)).isEqualTo(21)
    }

    @Test
    fun `짝수의 총합 구하기`() {
        // given
        val lambda = Lambda()

        // then
        assertThat(lambda.sumAllEven(lambda.numbers)).isEqualTo(12)
    }

    @Test
    fun `3 초과된 수의 합 구하기`() {
        // given
        val lambda = Lambda()

        // then
        assertThat(lambda.sumAllOverThree(lambda.numbers)).isEqualTo(15)
    }
}
