package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.component.Denomination
import blackjack.domain.card.component.Symbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class CardTest {

    @Test
    fun `원하는 카드 생성 테스트`() {
        // given
        val card = Card.of(Symbol.SPADE, Denomination.ACE)

        // then
        assertAll(
            { assertThat(card.symbol).isEqualTo(Symbol.SPADE) },
            { assertThat(card.denomination).isEqualTo(Denomination.ACE) }
        )
    }

    @Test
    fun `모든 카드의 개수 확인`() {
        // then
        assertThat(Card.ALL).hasSize(52)
    }
}
