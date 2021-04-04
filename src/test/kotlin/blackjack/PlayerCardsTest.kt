package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerCardsTest {
    @Test
    fun `카드를 맨 앞의 카드 한장을 뽑아서 리턴 해야 한다`() {
        val cards = GameCards()

        val card = cards.poll()

        assertThat(card).isEqualTo(Card(CardSuite.DIAMOND, CardNumber.ACE))
    }
}
