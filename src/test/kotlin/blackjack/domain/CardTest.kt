package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTest {

    @Test
    fun `카드를 만든다`() {
        val result = Card(Suit.HEART, Denomination.ACE)
        assertThat(result).isNotNull
    }

    @Test
    fun `점수를 제공한다`() {
        val card = Card(Suit.HEART, Denomination.ACE)
        val result = card.scores()
        assertThat(result).isNotEmpty
    }
}
