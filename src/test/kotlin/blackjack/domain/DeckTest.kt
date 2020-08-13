package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `draw() 카드를 한장 뽑는다`() {
        assertThat(
            Deck(Card.PACK).draw()
        ).isInstanceOf(Card::class.java)
    }
}
