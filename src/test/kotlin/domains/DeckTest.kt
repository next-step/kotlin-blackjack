package domains

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class DeckTest {
    @Test
    @DisplayName("deck에서 카드를 한장 뽑는다")
    fun `sut drawCard`() {
        // Arrange
        val deck = Deck(PokerNumber.values().toList(), PokerShape.values().toList())
        val initDeckSize = deck.deck.size

        // Act
        deck.drawCard()

        // Assert
        assertThat(deck.deck.size).isEqualTo(initDeckSize - 1)
    }
}
