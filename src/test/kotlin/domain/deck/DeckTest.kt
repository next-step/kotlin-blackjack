package domain.deck

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class DeckTest {

    @Test
    fun `deck 에 카드가 한장도 없는 경우 카드를 뽑을 수 없다`() {
        val deck = Deck(listOf())
        assertThrows<IllegalArgumentException> { deck.draw() }
    }
}
