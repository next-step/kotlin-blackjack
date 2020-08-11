package blackjack.domain

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

            override fun getDealCards(): List<Card> {
                return emptyList()
            }
        }

        // then
        assertThat(drawStrategy.fetchCard()).isEqualTo(expectedCard)
    }
}
