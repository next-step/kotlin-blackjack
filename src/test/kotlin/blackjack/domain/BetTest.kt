package blackjack.domain

import blackjack.playerWith
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetTest {
    @Test
    internal fun `게임에서 지면 배팅액을 잃는다`() {
        val pobi = CardPlayer.Player("pobi")
        assertThat(PlayerResult(pobi, PlayResult.LOSSES).income(Bet(pobi, 10_000)))
            .isEqualTo(-10_000)
    }

    @Test
    internal fun `플레이어가 지면 딜러는 얻는다`() {
        val pobi = CardPlayer.Player("pobi")

        assertThat(DealerAdjustment(listOf(PlayerResult(pobi, PlayResult.LOSSES)), listOf(Bet(pobi, 10_000))).income())
            .isEqualTo(10_000)
    }

    @Test
    internal fun `블랙잭(두 카드가 21)이면 배팅금액의 150%를 받는다`() {
        val pobi = playerWith("pobi", "A", "K")

        assertThat(PlayerResult(pobi, PlayResult.WINS).income(Bet(pobi, 10_000)))
            .isEqualTo(15_000)
    }

    @Test
    internal fun `무승부이면 0원을 받는다`() {
        val pobi = playerWith("pobi", "A", "K")

        assertThat(DealerAdjustment(listOf(PlayerResult(pobi, PlayResult.DRAWS)), listOf(Bet(pobi, 10_000))).income())
            .isEqualTo(0)
    }
}
