package blackjack.domain

import blackjack.domain.deck.Card
import blackjack.domain.deck.Deck
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suits
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeckTest {
    private val deck = Deck()

    @Test
    fun `52개의 카드를 가지고 있다`() {
        assertThat(deck.cards.size).isEqualTo(52)
    }

    @Test
    fun `앞에서부터 카드를 뽑을 수 있다`() {
        val actual = deck.pop()
        assertThat(actual).isEqualTo(Card(Suits.SPADES, Denomination.ACE))
    }

    @Test
    fun `리셋할 수 있다`() {
        deck.reset()
        assertThat(deck).isNotEqualTo(Card(Suits.SPADES, Denomination.ACE))
    }
}
