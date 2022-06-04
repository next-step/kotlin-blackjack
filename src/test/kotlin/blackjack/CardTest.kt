package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTest {
    @Test
    fun `같은 모양 같은 숫자이면 같은 객체이다 (동등성 비교)`() {
        val card = Card(CardShape.SPADE, CardNumber(1))
        assertThat(card).isEqualTo(Card(CardShape.SPADE, CardNumber(1)))
    }
}
