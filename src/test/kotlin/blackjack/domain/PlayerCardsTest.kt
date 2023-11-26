package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class PlayerCardsTest {

    @Test
    fun `N장의 카드를 추가할 경우 PlayerCards는 원소 개수가 N만큼 늘어난 새로운 카드 리스트를 갖는다`() {
        // given
        val playerCards = PlayerCards(
            listOf(
                Card(Suit.SPADES, Denomination.ACE),
                Card(Suit.HEARTS, Denomination.TEN),
                Card(Suit.CLUBS, Denomination.TWO),
            )
        )
        val originCardValues = playerCards.values

        // when
        playerCards.add(
            Card(Suit.DIAMONDS, Denomination.FIVE),
            Card(Suit.SPADES, Denomination.FOUR)
        )

        // then
        assertEquals(5, playerCards.values.size)
        assertNotEquals(originCardValues, playerCards.values)
    }
}
