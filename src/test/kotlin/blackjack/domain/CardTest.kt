package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드는 문양과 번호를 가진다`() {
        val card = Card(symbol = CardSymbol.HEART, number = CardNumber(1))

        assertThat(card.symbol).isEqualTo(CardSymbol.HEART)
        assertThat(card.number).isEqualTo(CardNumber(1))
    }
}
