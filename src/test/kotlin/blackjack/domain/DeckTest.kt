package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeckTest {

    @Test
    fun `Deck은 52장의 서로다른 Card로 구성된다`() {
        val deck = Deck.create()

        assertThat(deck.cards).isEqualTo(deck.cards)
        assertThat(deck.cards.toSet()).hasSize(52)
    }
}
