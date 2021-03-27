package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTypeTest {
    @Test
    fun `Ace개수를 파악한다(1개)`() {
        val cards = listOf(CardType.ACE)
        val actual = CardType.findAceCount(cards)
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `Ace개수를 파악한다(2개)`() {
        val cards = listOf(CardType.ACE, CardType.ACE)
        val actual = CardType.findAceCount(cards)
        assertThat(actual).isEqualTo(2)
    }
}
