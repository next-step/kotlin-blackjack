package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class SoftAcePointStrategyTest {
    @Test
    fun `Ace는 11점으로 계산한다`() {
        val softAcePointStrategy = SoftAcePointStrategy()

        val actualPoint = softAcePointStrategy.getPoint(Rank.Ace)
        val expectedPoint = 11

        assertThat(actualPoint).isEqualTo(expectedPoint)
    }

    @ParameterizedTest
    @EnumSource(value = Rank::class, names = ["Ace"], mode = EnumSource.Mode.EXCLUDE)
    fun `Ace가 아니면 원래의 점수로 계산한다`(rank: Rank) {
        val softAcePointStrategy = SoftAcePointStrategy()

        val actualPoint = softAcePointStrategy.getPoint(rank)
        val expectedPoint = rank.value

        assertThat(actualPoint).isEqualTo(expectedPoint)
    }
}
