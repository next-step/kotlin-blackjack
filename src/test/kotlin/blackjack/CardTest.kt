package blackjack

import blackjack.enums.CardPoint
import blackjack.enums.CardShape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `전달된 형태와 숫자에 맞게 블랙잭 카드를 꺼낼 수 있다`() {
        val card = Card.of(CardShape.SPADE, CardPoint.KING)
        assertThat(Pair(card.shape, card.point)).isEqualTo(Pair(CardShape.SPADE, CardPoint.KING))
    }
}
