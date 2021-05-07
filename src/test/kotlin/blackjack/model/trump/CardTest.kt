package blackjack.model.trump

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTest {
    @Test
    fun `각 카드는 종류별로 한 장만 뽑힐 수 있다`() {
        val deckOriginalSize = TrumpDeck().size
        val deck = TrumpDeck()

        val totalDraw = (0 until deckOriginalSize).map { deck.draw() }.toSet()

        assertThat(totalDraw.size).isEqualTo(deckOriginalSize)
    }
}
