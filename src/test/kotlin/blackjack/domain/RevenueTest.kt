package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class RevenueTest {
    @ParameterizedTest
    @ValueSource(ints = [10000, 200000, -1000])
    fun `Revenue는 블랙잭 게임의 결과로 얻은 손익을 보관한다`(input: Int) {
        assertThat(Revenue(input).value).isEqualTo(input)
    }
}
