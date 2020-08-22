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

class PlayersTest {
    @Test
    fun `플레이어 생성 테스트`() {
        val players = Players(listOf("a", "b").map { Player(it) }, Dealer())
        assertThat(players.size()).isEqualTo(3)
    }

    @Test
    fun `딜러와 플레이어 중 딜러가 0점인 경우 승자 테스트`() {
        val cards = Cards(
            listOf(
                Card(Suit.CLUBS, Denomination.ACE),
                Card(Suit.SPADES, Denomination.ACE),
                Card(Suit.SPADES, Denomination.NINE)
            )
        )
        val players = Players(listOf(Player("a", cards)), Dealer())
        players.calculateResult(players.dealer)
        val player = players[1]
        assertThat(player.score()).isEqualTo(21)
        assertThat(player.gameResult).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `딜러와 플레이어 동점 상황 승자 테스트`() {
        val cards = Cards(
            listOf(
                Card(Suit.CLUBS, Denomination.ACE),
                Card(Suit.SPADES, Denomination.ACE),
                Card(Suit.SPADES, Denomination.NINE)
            )
        )
        val players = Players(listOf(Player("a", cards)), Dealer(cards))
        players.calculateResult(players.dealer)
        val player = players[1]
        assertThat(player.score()).isEqualTo(21)
        assertThat(player.gameResult).isEqualTo(GameResult.LOSE)
    }
}
