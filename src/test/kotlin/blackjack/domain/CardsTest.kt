package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardsTest {
    @Test
    fun `add 메서드 호출 시 Card 리스트에 특정 카드가 추가되었는지 확인한다`() {
        // given
        val cards = Cards()
        val newCards = Card(
            denomination = Denomination.ACE,
            suit = Suit.SPADES
        )
        // when
        cards.add(newCards)
        // then
        assertThat(cards.value.contains(newCards))
        assertEquals(1, cards.value.size)
    }

    @Test
    fun `clear 메서드 호출시 기존 Card 리스트가 모두 비워지는지 확인한다`() {
        // given
        val cards = Cards()
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
        cards.clear()
        // then
        assertEquals(0, cards.value.size)
    }

    @Test
    fun `준비된 카드가 모두 소진되었을 경우 dec 메서드 호출시 IllegalStateException이 발생하는지 확인한다`() {
        // given
        val cards = Cards()

        assertThrows<IllegalStateException> { // then
            cards.dec() // when
        }
    }

    @Test
    fun `getFull 메서드 호출 시 52장의 카드가 생성되었는지 확인한다`() {
        // given
        val cards = Cards()
        // when
        val fullCards = cards.getFull()
        // then
        assertEquals(Cards.TOTAL_SIZE, fullCards.value.size)
    }

    @Test
    fun `52장의 카드 중 한 장을 뽑으면 51장의 카드가 남는다`() {
        // given
        val fullCards = Cards().getFull()
        // when
        fullCards.dec()
        // then
        assertEquals(Cards.TOTAL_SIZE - 1, fullCards.value.size)
    }
}
