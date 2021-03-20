package blackjack.domain.card

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

internal class CardDeckTest {
    @DisplayName("카드덱 생성")
    @Test
    fun initCard() {
        val cards = CardDeck()

        assertDoesNotThrow { cards }
    }
}
