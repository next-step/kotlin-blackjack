package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {

    @Test
    fun `CardDeck으로 부터 Card를 받아올 수 있다`() {
        val cardDeck = CardDeck()

        assertThat(cardDeck.next()).isInstanceOf(Card::class.java)
    }
}
