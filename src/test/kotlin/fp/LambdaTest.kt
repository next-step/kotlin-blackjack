package fp

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class LambdaTest {
    private val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

    @Test
    fun `Lambda 함수 테스트`() {
        Assertions.assertThat(Lambda().sumAll(numbers)).isEqualTo(21)
        Assertions.assertThat(Lambda().sumAllEven(numbers)).isEqualTo(12)
        Assertions.assertThat(Lambda().sumAllOverThree(numbers)).isEqualTo(15)
    }
}
