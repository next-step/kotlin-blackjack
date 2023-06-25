package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `카드는 문양과 숫자를 가진다`() {
        val card = Card(number = CardNumber.A, shape = CardShape.CLOVER)
        Assertions.assertThat(card.number).isEqualTo(CardNumber.A)
        Assertions.assertThat(card.shape).isEqualTo(CardShape.CLOVER)
    }
}
