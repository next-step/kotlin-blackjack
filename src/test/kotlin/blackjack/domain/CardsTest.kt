package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardsTest {
    @Test
    fun `addCard 메서드 호출 시 특정 카드가 추가된 새로운 Cards 객체가 생성되는지 확인한다`() {
        // given
        val cards = Cards.from()
        val newCard = Card(
            denomination = Denomination.ACE,
            suit = Suit.SPADES
        )

        // when
        val newCards = cards.addCard(newCard)

        // then
        assertThat(newCards.contains(newCard))
        assertEquals(1, newCards.size)
    }

    @Test
    fun `준비된 카드가 모두 소진되었을 경우 dec 메서드 호출시 IllegalStateException이 발생하는지 확인한다`() {
        // given
        val cards = Cards.from()

        assertThrows<IllegalStateException> { // then
            cards.dec() // when
        }
    }

    @Test
    fun `준비된 카드 한 장의 카드를 뽑아 반환한다`() {
        // given
        val cards = Cards.from()
        cards.add(
            Card(
                denomination = Denomination.ACE,
                suit = Suit.SPADES
            )
        )
        cards.add(
            Card(
                denomination = Denomination.EIGHT,
                suit = Suit.CLUBS
            )
        )

        // when
        val removedCard = cards.dec()

        // then
        assertEquals(1, cards.size)
        assertEquals(Denomination.ACE, removedCard.denomination)
        assertEquals(Suit.SPADES, removedCard.suit)
    }
}
