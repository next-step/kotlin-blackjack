package blackjack.player

import blackjack.playingcard.Cards
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

internal class HandTest {
    @Test
    fun `손패는 카드리스트를 가진다`() {
        assertDoesNotThrow { Hand(cards = Cards(listOf())) }
    }
}
