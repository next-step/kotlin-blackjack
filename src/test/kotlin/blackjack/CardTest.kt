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
    fun `ACE 카드 점수는 1 이다`() {
        assertThat(CardPoint.ACE.score).isEqualTo(1)
    }
}
