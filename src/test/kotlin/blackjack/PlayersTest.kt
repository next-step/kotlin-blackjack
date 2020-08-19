package blackjack

import blackjack.model.Card
import blackjack.model.Cards
import blackjack.model.Denomination
import blackjack.model.Player
import blackjack.model.Players
import blackjack.model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersTest {

    @Test
    fun `플레이어 생성 테스트`() {
        val players = Players(listOf("a", "b").map { Player(it) })
        assertThat(players.size()).isEqualTo(2)
    }

    @Test
    fun `플레이어 승자 테스트`() {
        val cards = Cards(
            listOf(
                Card(Suit.CLUBS, Denomination.ACE),
                Card(Suit.SPADES, Denomination.ACE),
                Card(Suit.SPADES, Denomination.NINE)
            )
        )
        val players = Players(listOf(Player("a", cards)))
        val player = players[0]
        assertThat(players.winner()[0]).isEqualTo(player)
    }
}
