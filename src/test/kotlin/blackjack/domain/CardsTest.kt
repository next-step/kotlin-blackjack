package blackjack.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class CardsTest {

    private val card1 = Card(Suit.HEART, Denomination.ACE)
    private val card2 = Card(Suit.CLUB, Denomination.ACE)
    private val card3 = Card(Suit.DIAMOND, Denomination.ACE)

    @Test
    fun `Cards 는 서로 다른 두장의 카드를 받는다`() {
        assertDoesNotThrow { Cards(card1, card2) }
    }

    @Test
    fun `Cards 는 같은 두장의 카드로 만들 경우 예외처리`() {
        assertThrows<IllegalArgumentException> { Cards(card1, card1) }
    }

    @Test
    fun `Cards 는 서로 다른 세장의 카드로 만들 경우 예외처리`() {
        assertThrows<IllegalArgumentException> { Cards(card1, card2, card3) }
    }
}
