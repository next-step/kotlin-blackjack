package blackjack

import blackjack.Card.Companion.CARDS
import blackjack.view.ResultType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드 뽑기`() {
        val cardSize = CARDS.size
        Card.getCard()
        assertThat(CARDS.size).isEqualTo(cardSize - 1)
    }
}