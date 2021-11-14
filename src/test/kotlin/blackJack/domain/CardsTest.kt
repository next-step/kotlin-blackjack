package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {

    @Test
    fun `카드 뭉치는 총 52장이다`() {
        // given
        val cards = Cards.create()

        // when
        val cardsSize = cards.getSize()

        // then
        assertThat(cardsSize).isEqualTo(52)
    }

    @Test
    fun `카드 뭉치(52장)에서 1장을 빼면 51장이다`() {
        // given
        val cards = Cards.create()
        val card = Card(Suit.DIAMONDS, Denomination.KING)

        // when
        val cardsSize = (cards - card).getSize()

        // then
        assertThat(cardsSize).isEqualTo(51)
    }
}
