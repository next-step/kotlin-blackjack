package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {

    @Test
    fun `카드들의 점수 계산을 검증한다`() {
        val cards = Cards()
        cards.add(Card(CardType.SPADE, CardNumber.ACE))
        cards.add(Card(CardType.SPADE, CardNumber.SIX))
        cards.add(Card(CardType.SPADE, CardNumber.FIVE))

        assertThat(cards.getScore()).isEqualTo(12)

        cards.add(Card(CardType.SPADE, CardNumber.SEVEN))

        assertThat(cards.getScore()).isEqualTo(19)
    }
}
