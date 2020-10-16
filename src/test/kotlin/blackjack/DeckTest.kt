package blackjack

import blackjack.domain.Deck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `덱 사이즈가 52가 맞는지 확인`() {
        assertThat(Deck.defaultDeck().size).isEqualTo(52)
    }
}
