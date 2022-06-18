package blackjack.domain

import blackjack.domain.Denomination.ACE
import blackjack.domain.Suit.SPADE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTest {

    @Test
    fun `card는 suit와 denomination을 가진다`() {
        val card = Card(SPADE, ACE)

        assertThat(card.suit).isEqualTo(SPADE)
        assertThat(card.denomination).isEqualTo(ACE)
    }

    @Test
    fun `card는 string으로 변환할 수 있다`() {
        val card = Card(SPADE, ACE)

        assertThat(card.toString()).isEqualTo("♠A")
    }
}
