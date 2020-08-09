package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {

    @Test
    fun `모든 카드 뽑기`() {
        val deck = mutableListOf<Deck>()
        repeat(Deck.TOTAL_DECK_SIZE) {
            deck.add(Deck.pop())
        }
        assertThat(deck.size).isEqualTo(Deck.TOTAL_DECK_SIZE)
    }
}
