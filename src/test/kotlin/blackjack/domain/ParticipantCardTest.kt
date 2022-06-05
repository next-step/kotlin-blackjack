package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantCardTest {
    @Test
    fun `카드 추가 테스트`() {
        val deck = MockCardDeck(Card(Card.CardPattern.CLUBS, Card.Denomination.EIGHT))
        val playerCards = ParticipantCard()
        playerCards.add(deck.draw())

        assertThat(playerCards.playerCards.size).isEqualTo(1)
    }
}
