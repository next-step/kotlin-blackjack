package blackjack

import blackjack.domain.Dealer
import blackjack.domain.enums.CardPoint
import blackjack.domain.enums.CardShape
import blackjack.domain.interfaces.FixedCardFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 카드를 줄 수 있다`() {
        val card = Dealer().give(FixedCardFactory(CardShape.CLOVER, CardPoint.FIVE))
        assertThat(Pair(card.shape, card.point)).isEqualTo(Pair(CardShape.CLOVER, CardPoint.FIVE))
    }
}
