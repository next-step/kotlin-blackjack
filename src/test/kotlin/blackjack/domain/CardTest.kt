package blackjack.domain

import blackjack.domain.deck.Card
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suits
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTest {
    @Test
    fun `카드는 모양과 끗수로 이뤄져있다`() {
        val card = Card(Suits.SPADES, Denomination.ACE)
        assertThat(card.suits).isEqualTo(Suits.SPADES)
        assertThat(card.denomination).isEqualTo(Denomination.ACE)
    }

    @Test
    fun `모양과 끗수가 같다면 같은 카드이다`() {
        val card1 = Card(Suits.SPADES, Denomination.ACE)
        val card2 = Card(Suits.SPADES, Denomination.ACE)
        assertThat(card1).isEqualTo(card2)
    }
}
