package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetTest {
    @Test
    internal fun `게임에서 지면 배팅액을 잃는다`() {
        val pobi = CardPlayer.Player("pobi")
        assertThat(PlayerResult(pobi, losses = 1).income(Bet(pobi, 10_000)))
            .isEqualTo(-10_000)
    }

    @Test
    internal fun `플레이어가 지면 딜러는 얻는다`() {
        val pobi = CardPlayer.Player("pobi")

        assertThat(DealerAdjustment(PlayerResult(pobi, losses = 1), Bet(pobi, 10_000)).income())
            .isEqualTo(-10_000)
    }
}
