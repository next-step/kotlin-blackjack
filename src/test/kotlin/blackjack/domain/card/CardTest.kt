package blackjack.domain.card

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `getOrder 시 denomination 이 가진 order 를 반환한다`() {
        val card = Card(
            CardPattern.DIAMOND,
            CardDenomination.ACE,
        )

        val actual = card.getOrder()

        assertEquals(CardDenomination.ACE.order, actual)
    }

    @Test
    fun `getValue 시 Denomination 이 가진 value 를 반환한다`() {
        val card = Card(
            CardPattern.DIAMOND,
            CardDenomination.KING,
        )

        val actual = card.getValue(0)

        assertEquals(10, actual)
    }
}
