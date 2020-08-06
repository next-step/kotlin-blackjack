package fp

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LambdaTest {
    val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

    @Test
    fun `sumAll() 전체 합계 테스트`() {
        assertThat(Lambda().sumAll(numbers)).isEqualTo(21)
    }

    @Test
    fun `sumAllEven() 짝수의 전체 합계`() {
        assertThat(Lambda().sumAllEven(numbers)).isEqualTo(12)
    }

    @Test
    fun `sumAllOverThree() 3 이상의 값만 합계`() {
        assertThat(Lambda().sumAllOverThree(numbers)).isEqualTo(15)
    }
}
