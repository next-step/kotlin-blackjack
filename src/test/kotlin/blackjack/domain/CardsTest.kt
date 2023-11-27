package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class CardsTest {

    @Test
    fun `N장의 카드를 추가할 경우 PlayerCards는 원소 개수가 N만큼 늘어난 새로운 카드 리스트를 갖는다`() {
        // given
        val cards = Cards(
            listOf(
                Card(Suit.SPADES, Denomination.ACE),
                Card(Suit.HEARTS, Denomination.TEN),
                Card(Suit.CLUBS, Denomination.TWO),
            )
        )
        val originCardValues = cards.values

        // when
        cards.add(
            Card(Suit.DIAMONDS, Denomination.FIVE),
            Card(Suit.SPADES, Denomination.FOUR)
        )

        // then
        assertEquals(5, cards.values.size)
        assertNotEquals(originCardValues, cards.values)
    }
}
