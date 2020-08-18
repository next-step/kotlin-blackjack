package blackjack

import blackjack.model.Card
import blackjack.model.Denomination
import blackjack.model.Player
import blackjack.model.Players
import blackjack.model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersTest {
    private val players = Players(listOf("a", "b").map { Player(it) })

    @Test
    fun `플레이어 생성 테스트`() {
        assertThat(players.size()).isEqualTo(2)
    }

    @Test
    fun `플레이어 승자 테스트`() {
        val player = players[0]
        player.draw(Card(Suit.CLUBS, Denomination.A))
        player.draw(Card(Suit.SPADES, Denomination.A))
        player.draw(Card(Suit.SPADES, Denomination.TEN))
        player.draw(Card(Suit.SPADES, Denomination.NINE))
        assertThat(players.winner()[0]).isEqualTo(player)
    }
}
