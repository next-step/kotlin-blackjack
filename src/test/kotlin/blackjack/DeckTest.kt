package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {

    @Test
    fun `총 카드 개수 확인`() {
        assertThat(Deck.cards.size).isEqualTo(Deck.TOTAL_DECK_SIZE)
    }
}
