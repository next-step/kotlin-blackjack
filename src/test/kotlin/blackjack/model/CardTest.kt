package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `모든 카드 뽑기`() {
        val card = mutableListOf<Card>()
        repeat(Card.TOTAL_CARD_AMOUNT) {
            card.add(Card.pop())
        }
        assertThat(card.size).isEqualTo(Card.TOTAL_CARD_AMOUNT)
    }
}
