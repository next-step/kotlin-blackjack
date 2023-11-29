package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

internal class DeckTest {
    @Test
    fun `Deck은 52장의 카드를 갖는다 따라서 그 이상의 카드를 draw하면 오류가 발생한다`() {
        // given, when
        val deck = Deck.create()
        val deckSize = 52

        // then
        assertThrows<IllegalArgumentException> { deck.draw(deckSize + 1) }
    }

    @Test
    fun `Deck은 52장의 카드를 갖는다`() {
        // given, when
        val deck = Deck.create()
        val deckSize = 52

        // then
        assertThat(deck.draw(deckSize).size).isEqualTo(deckSize)
    }
}
