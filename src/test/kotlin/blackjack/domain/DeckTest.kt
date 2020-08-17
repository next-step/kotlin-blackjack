package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeckTest {
    private val deck = Deck

    @Test
    fun `A Spades 부터 King Clubs까지 52개의 카드 묶음이다`() {
        assertThat(deck.cards.size).isEqualTo(52)
        assertThat(deck.cards.first()).isEqualTo(Card(Suits.SPADES, Denomination.ACE))
        assertThat(deck.cards.last()).isEqualTo(Card(Suits.CLUBS, Denomination.KING))
    }

    @Test
    fun `섞을 수 있다`() {
        val shuffledDeck = deck.shuffle()

        assertThat(deck.cards.first()).isNotEqualTo(shuffledDeck.first())
    }

    @Test
    fun `원하는 숫자 만큼 앞에서부터 뽑을 수 있다`() {
        val takedDeck = deck.take(1)

        assertThat(takedDeck.size).isEqualTo(1)
        assertThat(takedDeck.first()).isEqualTo(Card(Suits.SPADES, Denomination.ACE))

        val takedDeck2 = deck.take(2)
        assertThat(takedDeck2.size).isEqualTo(2)
    }
}
