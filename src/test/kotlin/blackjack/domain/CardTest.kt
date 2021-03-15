package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTest {
    @Test
    fun `카드 무늬, 점수 타입을 가진다`() {
        val card = Card.of(CardSymbol.CLOVER, CardNumber.ACE)

        assertThat(card.symbol).isEqualTo(CardSymbol.CLOVER)
        assertThat(card.number).isEqualTo(CardNumber.ACE)
    }
}
