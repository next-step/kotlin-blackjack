package blackjack.domain

import blackjack.domain.deck.Card
import blackjack.domain.deck.Deck
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suits
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeckTest {

    @Test
    fun `52개의 카드를 가지고 있다`() {
        val deck = Deck
        assertThat(deck.cards.size).isEqualTo(52)
    }

    @Test
    fun `앞에서부터 카드를 뽑을 수 있다`() {
        val deck = Deck
        val actual = deck.pop()
        assertThat(actual).isInstanceOf(Card::class.java)
    }

    @Test
    fun `리셋할 수 있다`() {
        val deck = Deck
        deck.reset()
        assertThat(deck).isNotEqualTo(Card(Suits.SPADES, Denomination.ACE))
    }
}
