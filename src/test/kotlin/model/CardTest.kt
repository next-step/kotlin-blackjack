package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTest {
    @Test
    fun `중복 없이 카드를 뽑는다`() {
        val cards = mutableListOf<Card>()
        for (i in TOTAL_CARD_SIZE) {
            cards.add(Card.generate())
        }
        assertThat(cards.toSet().size).isSameAs(52)
    }

    companion object {
        private val TOTAL_CARD_SIZE = 1..52
    }
}
