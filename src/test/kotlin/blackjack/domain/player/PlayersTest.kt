package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private fun `starting cards`() = listOf(Card.Two(CardSuit.CLOVER), Card.Three(CardSuit.CLOVER))
class PlayersTest {
    @Test
    fun `Players 는 딜러와 여러명의 플레이어로 구성되어 있다`() {
        val dealer = Dealer(`starting cards`())
        val players = listOf(Player("a", `starting cards`()), Player("b", `starting cards`()))
        val allPlayers = Players(dealer, players)

        assertThat(allPlayers.dealer).isEqualTo(dealer)
        assertThat(allPlayers.players).isEqualTo(players)
    }
}
