package blackjack.playingcard

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

internal class CardsTest {
    @Test
    fun `카드뭉치는 카드리스트로 생성된다`() {
        assertDoesNotThrow { Cards(listOf(Card(Suit.HEARTS, Symbol.ACE))) }
    }
}
