package blackjack.card

import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun createCard() {
        Card(Suit.CLUB, CardSymbol.ONE)
    }
}
