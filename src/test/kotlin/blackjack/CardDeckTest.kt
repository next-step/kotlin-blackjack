package blackjack

import blackjack.domain.Card
import blackjack.domain.CardDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {

    @Test
    fun `CardDeck으로 부터 Card를 받아올 수 있다`() {
        val cardDeck = CardDeck()

        assertThat(cardDeck.next()).isInstanceOf(Card::class.java)
    }

    @Test
    fun `CardDeck으로 부터 받은 Card는 서로 다른 Card이다`() {
        val cardDeck = CardDeck()

        val card1 = cardDeck.next()
        val card2 = cardDeck.next()

        println("card1 == $card1, card2 == $card2")
        assertThat(card1).isNotEqualTo(card2)
    }
}
