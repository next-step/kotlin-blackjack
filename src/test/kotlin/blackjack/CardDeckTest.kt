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
    fun `CardDeck에 Card가 다 떨어지면 Card 52장을 새로 추가한다`() {
        val cardDeck = CardDeck()

        assertThat(cardDeck.size).isEqualTo(52)

        repeat(cardDeck.size) {
            cardDeck.next()
        }
        assertThat(cardDeck.size).isEqualTo(0)

        cardDeck.next()
        assertThat(cardDeck.size).isEqualTo(51)
    }
}
