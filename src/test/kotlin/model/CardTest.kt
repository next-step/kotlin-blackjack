package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTest {
    @Test
    fun `중복 없이 카드를 뽑느다`() {
        val cards = mutableListOf<Card>()
        for (i in 1..52) {
            cards.add(Card.generateCard())
        }
        assertThat(cards.toSet().size).isSameAs(52)
    }
}
