package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayResultTest {
    private val pobi = CardPlayer.Player("pobi")
        .apply {
            accept(Card("A", Symbol.DIAMONDS))
            accept(Card("K", Symbol.DIAMONDS))
        }
    private val jason = CardPlayer.Player("jason")
        .apply {
            accept(Card("K", Symbol.DIAMONDS))
            accept(Card("2", Symbol.DIAMONDS))
        }
    private val dealer = CardPlayer.Dealer()
        .apply {
            accept(Card("K", Symbol.DIAMONDS))
            accept(Card("K", Symbol.DIAMONDS))
        }

    @Test
    internal fun `승패를 계산한다`() {
        val result = Players(listOf(pobi, jason), dealer).gameResult()

        assertThat(result).contains(
            PlayerResult(dealer, wins = 1, losses = 1),
            PlayerResult(pobi, wins = 1),
            PlayerResult(jason, losses = 1)
        )
    }
}
