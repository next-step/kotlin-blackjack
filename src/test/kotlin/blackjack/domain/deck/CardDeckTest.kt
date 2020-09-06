package blackjack.domain.deck

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.LinkedList

class CardDeckTest {

    @DisplayName("카드덱이 비면 덱을 자동으로 초기화한다")
    @Test
    fun resetCardDeck() {
        val cardDeck = CardDeck(LinkedList(listOf(Card(Pip.ACE, Suit.CLUB))))
        cardDeck.pop()
        cardDeck.pop()
        assertThat(cardDeck.size()).isEqualTo(51)
    }
}
