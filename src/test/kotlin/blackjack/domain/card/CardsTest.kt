package blackjack.domain.card

import blackjack.domain.cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardsTest {

    @Test
    fun `ACE가 없는 경우 point계산`() {
        val cards = Cards(cards(CardType.QUEEN, CardType.JACK))

        val point = cards.calculatePoint()
        assertThat(point).isEqualTo(20)
    }

    @Test
    fun `ACE가 있고 21을 넘지 않은 경우 point계산`() {
        val cards = Cards(cards(CardType.ACE, CardType.JACK))

        val point = cards.calculatePoint()
        assertThat(point).isEqualTo(21)
    }

    @Test
    fun `ACE가 1개 있고 21을 넘은 경우 point계산`() {
        val cards = cards(CardType.ACE, CardType.JACK, CardType.TWO)

        val point = cards.calculatePoint()
        assertThat(point).isEqualTo(13)
    }

    @Test
    fun `ACE가 2개 있고 21을 넘은 경우 point계산`() {
        val cards = cards(CardType.ACE, CardType.ACE, CardType.JACK, CardType.TWO)

        val point = cards.calculatePoint()
        assertThat(point).isEqualTo(14)
    }
}
