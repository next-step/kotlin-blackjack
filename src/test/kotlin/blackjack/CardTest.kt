package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.CardShape
import blackjack.domain.card.CardSymbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTest {
    @Test
    fun `같은 모양 같은 숫자이면 같은 객체이다 (동등성 비교)`() {
        val card = Card(CardShape.SPADE, CardSymbol.ACE)
        assertThat(card).isEqualTo(Card(CardShape.SPADE, CardSymbol.ACE))
    }
}
