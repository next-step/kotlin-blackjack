package blackjack

import model.AbstractPlayer
import model.Cards
import model.Dealer
import model.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class BlackJackGameTest {
    @Test
    @DisplayName("player 는 카드를 받는다")
    fun `receiveCard`() {
        val player = Player("hello")
        val players = listOf<AbstractPlayer>(player)
        val dealer = Dealer(Cards())
        val blackJackGame = BlackJackGame(players, dealer)
        blackJackGame.receiveCard(player)
        assertThat(player.cards).hasSize(1)
    }
}
