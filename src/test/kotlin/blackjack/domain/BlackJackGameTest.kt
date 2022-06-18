package blackjack.domain

import blackjack.view.GameView
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackGameTest {

    @Test
    fun `블랙잭 게임 카드분배 테스트`() {
        val deck = MockCardDeck(Card(Card.CardPattern.CLUBS, Card.Denomination.EIGHT))
        val dealer = Dealer("딜러")
        val players = listOf("molly", "jayce").map { Player(it, 10000, drawable = GameView) }
        val blackJackGame = BlackJackGame.of(dealer, players, deck)
        blackJackGame.firstCardDistribution()

        assertThat(blackJackGame.participants.first().participantCards.score()).isEqualTo(16)
    }
}
