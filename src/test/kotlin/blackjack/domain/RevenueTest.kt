package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class RevenueTest {
    @ParameterizedTest
    @ValueSource(ints = [10000, 200000, -1000])
    fun `Revenue는 블랙잭 게임의 결과로 얻은 손익을 보관한다`(input: Int) {
        assertThat(Revenue(input).value).isEqualTo(input)
    }

    @Test
    fun `reverse를 통해 이득을 손해로, 손해를 이득으로 바꿀 수 있다`() {
        val revenue = Revenue(10_000)
        val expected = Revenue(-10_000)
        assertThat(revenue.reverse()).isEqualTo(expected)
    }
}
