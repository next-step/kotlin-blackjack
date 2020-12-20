package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {

    @Test
    fun `Check initial Deck size`() {
        assertThat(Deck().size()).isEqualTo(48)
    }
}
