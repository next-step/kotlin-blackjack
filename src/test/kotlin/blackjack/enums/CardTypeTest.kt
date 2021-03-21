package blackjack.enums

import blackjack.domain.Card
import blackjack.domain.CardShape
import blackjack.domain.CardType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTypeTest {
    @Test
    fun `Ace개수를 파악한다(1개)`() {
        val cards = setOf(Card(CardShape.CLOVER, CardType.ACE))
        val actual = CardType.findAceCount(cards)
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `Ace개수를 파악한다(2개)`() {
        val cards = setOf(Card(CardShape.CLOVER, CardType.ACE), Card(CardShape.HEART, CardType.ACE))
        val actual = CardType.findAceCount(cards)
        assertThat(actual).isEqualTo(2)
    }
}
