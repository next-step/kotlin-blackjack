package fp

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LambdaTest {
    val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

    @Test
    @DisplayName("더하기 테스트")
    fun sumtest() {
        assertThat(Lambda().sumAll(numbers)).isEqualTo(21)
    }

    @Test
    @DisplayName("더하기 테스트")
    fun sumeventest() {
        assertThat(Lambda().sumAllEven(numbers)).isEqualTo(12)
    }

    @Test
    @DisplayName("더하기 테스트")
    fun sumoverthreetest() {
        assertThat(Lambda().sumAllOverThree(numbers)).isEqualTo(15)
    }
}