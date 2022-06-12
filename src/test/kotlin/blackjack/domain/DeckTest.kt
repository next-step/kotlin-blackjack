package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeckTest {

    @Test
    fun `Deck을 만들 수 있다`() {
        val deck = Deck(
            listOf(
                Card(Suit.SPADE, Denomination.ACE),
                Card(Suit.SPADE, Denomination.TWO)
            )
        )
        assertThat(deck.cards).hasSize(2)
    }

    @Test
    fun `Deck을 생성하면 52장의 서로다른 Card로 구성된다`() {
        val deck = Deck.create()

        assertThat(deck.cards).isEqualTo(deck.cards)
        assertThat(deck.cards.toSet()).hasSize(52)
    }

    @Test
    fun `Deck을 섞으면 순서가 변경된다`() {
        val deck = Deck.create()
        val cards = deck.cards

        deck.shuffle()

        assertThat(deck.cards).isNotEqualTo(cards)
    }
}
