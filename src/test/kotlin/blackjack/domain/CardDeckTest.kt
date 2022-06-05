package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `덱 카드 뽑기 테스트`() {
        val cardDeck = CardDeck(Card.createDeck())
        assertThat(cardDeck.draw()).isInstanceOf(Card::class.java)
    }

    @Test
    fun `카드 뽑기 테스트`() {
        val cardDeck = MockCardDeck(Card(Card.CardPattern.CLUBS, Card.Denomination.EIGHT)).draw()
        assertThat(cardDeck).isEqualTo(Card(Card.CardPattern.CLUBS, Card.Denomination.EIGHT))
    }
}
