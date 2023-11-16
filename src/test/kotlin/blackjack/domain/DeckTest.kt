@file:Suppress("NonAsciiCharacters")

package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `덱 하나의 초기 카드 수는 52장이다`() {
        val deck = Deck()

        val cards = deck.cards

        assertThat(cards).hasSize(52)
    }
}
