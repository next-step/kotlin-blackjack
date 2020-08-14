package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackGameTest {

    @Test
    fun `딜러와 참여자에게 카드 2장씩 나눠주기`() {
        val dealer = Dealer("dealer")
        val players = listOf("moshi", "gson").map(::Player)
        val game = BlackJackGame(dealer = dealer, players = players)

        game.run {
            initCardForDealer()
            initCardForPlayers()
        }

        assertThat(dealer.myCards.size).isEqualTo(2)
        assertThat(players.map { it.myCards.size }).isEqualTo(listOf(2, 2))
    }
}
