package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackGameTest {

    @Test
    fun `블랙잭 게임 카드분배 테스트`() {
        val deck = MockCardDeck(Card(Card.CardPattern.CLUBS, Card.Denomination.EIGHT))
        val dealer = Dealer("딜러")
        val players = listOf("molly", "jayce").map { Participant(it) }
        val blackJackGame = BlackJackGame.of(dealer, players, deck)
        blackJackGame.firstCardDistribution()

        assertThat(blackJackGame.players[0].playerCards.score()).isEqualTo(16)
    }
}
