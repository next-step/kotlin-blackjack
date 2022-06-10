package balckjac.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTest {

    @Test
    fun `카드는 suit, denomination 정보를 갖는다`() {
        val card = Card(
            suit = Suit.DIA,
            denomination = Denomination.ACE
        )

        assertThat(card.suit).isEqualTo(Suit.DIA)
        assertThat(card.denomination).isEqualTo(Denomination.ACE)
    }
}
