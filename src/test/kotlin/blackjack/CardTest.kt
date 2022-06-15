package blackjack

import blackjack.domain.Card
import blackjack.domain.enums.CardPoint
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드 한 팩은 52장이다`() {
        assertThat(Card.pack().size).isEqualTo(52)
    }

    @Test
    fun `ACE 카드는 1 혹은 11 값이 될 수 있다`() {
        assertThat(Pair(CardPoint.ACE.min, CardPoint.ACE.max)).isEqualTo(Pair(1, 11))
    }
}
