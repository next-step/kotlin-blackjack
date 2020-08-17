package blackjack.domain.card

import blackjack.domain.card.component.Denomination
import blackjack.domain.card.component.Symbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeckTest {

    @Test
    fun `원하는 카드를 한 장 뽑는지 확인`() {
        // given
        val expectedCard = Card.of(Symbol.SPADE, Denomination.ACE)
        val deck = Deck(object : DrawStrategy {
            override fun fetchCard(): Card {
                return expectedCard
            }
        })

        // then
        assertThat(deck.fetchCard()).isEqualTo(expectedCard)
    }

    @Test
    fun `처음 두장을 원하는 카드로 뽑는지 확인`() {
        // given
        val expectedCard = Card.of(Symbol.SPADE, Denomination.ACE)
        val deck = Deck(object : DrawStrategy {
            override fun fetchCard(): Card {
                return expectedCard
            }
        })

        // then
        assertThat(deck.getDealCards()).isEqualTo(listOf(expectedCard, expectedCard))
    }
}
