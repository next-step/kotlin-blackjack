package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {

    @Test
    fun `덱에 남아있는 카드 개수를 알 수 있다`() {
        val deck = Deck(emptyList())
        assertThat(deck.size).isZero
    }
}
