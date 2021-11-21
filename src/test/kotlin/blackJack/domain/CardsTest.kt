package blackJack.domain

import blackJack.domain.card.Card
import blackJack.domain.card.Cards
import blackJack.domain.card.Denomination
import blackJack.domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {

    @Test
    fun `카드 뭉치는 총 52장이다`() {
        // given
        val cards = Cards.create()

        // when
        val cardsSize = cards.size

        // then
        assertThat(cardsSize).isEqualTo(52)
    }

    @Test
    fun `카드 뭉치(52장)에서 1장을 빼면 51장이다`() {
        // given
        val cards = Cards.create()
        val card = Card(Suit.DIAMONDS, Denomination.KING)

        // when
        val cardsSize = (cards - card).size

        // then
        assertThat(cardsSize).isEqualTo(51)
    }
}
