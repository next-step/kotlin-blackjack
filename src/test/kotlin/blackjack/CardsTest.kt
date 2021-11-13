package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class CardsTest {

    @Test
    fun `카드 묶음을 생성할 수 있다`() {
        val card1 = Card(Suit.CLUB, Denomination.TWO)
        val card2 = Card(Suit.HEART, Denomination.SEVEN)
        val cards = listOf(card1, card2)

        assertThat(Cards.from(cards)).isNotNull
    }

    @Test
    fun `중복된 카드가 존재하면 예외를 던진다`() {
        val card1 = Card(Suit.CLUB, Denomination.TWO)
        val card2 = Card(Suit.CLUB, Denomination.TWO)

        val cards = listOf(card1, card2)

        assertThrows<IllegalArgumentException> { Cards.from(cards) }
    }
}
