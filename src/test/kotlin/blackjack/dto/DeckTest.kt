package blackjack.dto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class DeckTest {

    @Test
    fun `덱을 생성한다`() {
        val deck = Deck.newDeck()
        assertAll(
            { assertThat(deck).hasSize(52) },
            { assertThat(deck.toSet()).hasSize(52) }
        )
    }
}
