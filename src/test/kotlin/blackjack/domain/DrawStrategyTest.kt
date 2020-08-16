package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.component.Denomination
import blackjack.domain.card.DrawStrategy
import blackjack.domain.card.component.Symbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DrawStrategyTest {

    @Test
    fun `원하는 카드를 한장 뽑는지 확인`() {
        // given
        val expectedCard = Card.of(Symbol.SPADE, Denomination.ACE)
        val drawStrategy = object : DrawStrategy {
            override fun fetchCard(): Card {
                return expectedCard
            }
        }

        // then
        assertThat(drawStrategy.fetchCard()).isEqualTo(expectedCard)
    }
}
