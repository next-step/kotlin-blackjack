package blackjack.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class HardAcePointStrategyTest {
    @ParameterizedTest
    @EnumSource(value = Rank::class)
    fun `모든 카드는 원래의 점수로 계산한다`(rank: Rank) {
        val hardAcePointStrategy = HardAcePointStrategy()

        val actualPoint = hardAcePointStrategy.getPoint(rank)
        val expectedPoint = rank.value

        Assertions.assertThat(actualPoint).isEqualTo(expectedPoint)
    }
}
