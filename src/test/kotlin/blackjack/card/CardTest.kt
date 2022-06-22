package blackjack.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow

class CardTest {

    @Test
    fun createCard() {
        assertDoesNotThrow { Card(Suit.CLUB, CardSymbol.ACE) }
    }

    @Test
    fun `카드가 에이스카드인지 알 수 있다`() {
        val card1 = Card(Suit.CLUB, CardSymbol.ACE)
        val card2 = Card(Suit.CLUB, CardSymbol.KING)

        assertAll(
            { assertThat(card1.isAceCard()).isEqualTo(true) },
            { assertThat(card2.isAceCard()).isEqualTo(false) },
        )
    }
}
