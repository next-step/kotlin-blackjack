package blackjack.enums

import blackjack.domain.Card
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTypeTest {
    @Test
    fun `Ace개수를 파악한다(1개)`() {
        val cards = setOf(Card(CardShape.Clover, CardType.Ace))
        val actual = CardType.findAceCount(cards)
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `Ace개수를 파악한다(2개)`() {
        val cards = setOf(Card(CardShape.Clover, CardType.Ace), Card(CardShape.Heart, CardType.Ace))
        val actual = CardType.findAceCount(cards)
        assertThat(actual).isEqualTo(2)
    }
}
