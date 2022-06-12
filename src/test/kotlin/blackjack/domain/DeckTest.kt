package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class DeckTest {

    @Test
    fun `Deck을 만들 수 있다`() {
        val deck = createDummyDeck()

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

    @Test
    fun `Deck에서 카드를 한 장 뽑을 수 있다`() {
        val deck = createDummyDeck()
        val card1 = deck.draw()
        val card2 = deck.draw()

        assertThat(card1).isEqualTo(Card(Suit.SPADE, Denomination.ACE))
        assertThat(card2).isEqualTo(Card(Suit.SPADE, Denomination.TWO))
    }

    @Test
    fun `Deck에서 카드를 두 장 뽑을 수 있다`() {
        val deck = createDummyDeck()
        val cards = deck.draw(2)

        assertThat(cards[0]).isEqualTo(Card(Suit.SPADE, Denomination.ACE))
        assertThat(cards[1]).isEqualTo(Card(Suit.SPADE, Denomination.TWO))
    }

    @Test
    fun `빈 Deck에서 카드를 뽑으면 exception이 발생한다`() {
        val deck = createDummyDeck()
        deck.draw()
        deck.draw()

        assertThrows<RuntimeException> { deck.draw() }
    }

    private fun createDummyDeck(): Deck {
        return Deck(
            listOf(
                Card(Suit.SPADE, Denomination.ACE),
                Card(Suit.SPADE, Denomination.TWO)
            )
        )
    }
}
