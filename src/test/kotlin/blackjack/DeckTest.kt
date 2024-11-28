package blackjack

import blackjack.domain.Deck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {

    @Test
    fun `덱은 52장의 카드를 가진다`() {
        val deck = Deck()
        assertThat(deck.cards.size).isEqualTo(52)
    }

    @Test
    fun `덱에서 한장의 카드를 뽑을 수 있다`() {
        val deck = Deck()
        val card = deck.drawCard()
        assertThat(card).isNotNull
        assertThat(deck.cards.size).isEqualTo(51)
    }
}
