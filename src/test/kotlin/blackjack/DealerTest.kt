package blackjack

import blackjack.model.Card
import blackjack.model.Cards
import blackjack.model.Dealer
import blackjack.model.Denomination
import blackjack.model.GameResult
import blackjack.model.Player
import blackjack.model.Players
import blackjack.model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러 클래스 상속 테스트`() {
        val dealer = Dealer()
        assertThat(dealer is Player).isEqualTo(true)
        assertThat(dealer is Dealer).isEqualTo(true)
    }

    @Test
    fun `딜러 승리 테스트`() {
        val cards = Cards(
            listOf(
                Card(Suit.CLUBS, Denomination.ACE),
                Card(Suit.SPADES, Denomination.NINE)
            )
        )
        val players = Players(listOf(Player("a", cards)), Dealer(cards))
        players.calculateResult(players.dealer)
        val player = players[0]
        assertThat(player.score()).isEqualTo(20)
        assertThat(player.gameResult).isEqualTo(GameResult.WIN)
    }
}
