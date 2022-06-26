package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `덱에서 카드를 뽑는다`() {
        val deck = Deck()

        assertThat(deck.draw()).isNotNull
    }
}
