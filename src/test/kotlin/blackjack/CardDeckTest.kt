package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.state.CARD_HEART_ACE
import blackjack.state.CARD_HEART_KING
import blackjack.state.CARD_HEART_TWO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.util.LinkedList

class CardDeckTest {

    @Test
    fun `CardDeck으로 부터 Card를 받아올 수 있다`() {
        val cardDeck = CardDeck()

        assertThat(cardDeck.next()).isInstanceOf(Card::class.java)
    }

    @Test
    fun `CardDeck에 있는 카드를 순서대로 Card를 뽑을 수 있다`() {
        val cardDeck = CardDeck(cardQueue = LinkedList(listOf(CARD_HEART_ACE, CARD_HEART_TWO, CARD_HEART_KING)))

        assertAll(
            {
                assertThat(cardDeck.next()).isEqualTo(CARD_HEART_ACE)
            },
            {
                assertThat(cardDeck.next()).isEqualTo(CARD_HEART_TWO)
            },
            {
                assertThat(cardDeck.next()).isEqualTo(CARD_HEART_KING)
            }
        )
    }
}
