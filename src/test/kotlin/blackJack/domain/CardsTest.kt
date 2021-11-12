package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {

    @Test
    fun `카드의 숫자는 총 52장이다`() {
        // given
        val cards = Cards.create()

        // when
        val cardsSize = cards.getSize()

        // then
        assertThat(cardsSize).isEqualTo(CARD_SIZE)
    }

    companion object {
        private const val CARD_SIZE = 52
    }
}
