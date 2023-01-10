package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeckTest {
    @Test
    fun `중복 없이 모든 카드를 뽑는다`() {
        var cards = mutableListOf<Card>()
        val totalCard = CardNumber.values().flatMap {
            CardShape.values().map {
            }
        }

        totalCard.forEach { _ ->
            cards.add(Deck.getCard())
        }

        assertThat(cards.size).isEqualTo(totalCard.size)
    }
}
