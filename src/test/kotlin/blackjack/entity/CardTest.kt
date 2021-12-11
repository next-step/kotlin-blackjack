package blackjack.entity

import blackjack.entity.enums.Denomination
import blackjack.entity.enums.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `원하는 카드가 생성되는지 확인한다`() {
        val card = Card(Suit.SPADE, Denomination.FIVE)
        assertThat(card.suit).isEqualTo(Suit.SPADE)
        assertThat(card.denomination).isEqualTo(Denomination.FIVE)
    }
}
