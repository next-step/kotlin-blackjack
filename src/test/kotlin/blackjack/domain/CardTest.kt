package blackjack.domain

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

    @Test
    fun `카드는 suit, denomination 을 조합하여 실제 카드 이름을 생성한다`() {
        val card = Card(
            suit = Suit.DIA,
            denomination = Denomination.FIVE
        )

        assertThat(card.fullName).isEqualTo("다이아몬드(◆)5")
    }
}
