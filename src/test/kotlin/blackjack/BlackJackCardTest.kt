package blackjack

import blackjack.domain.BlackJackCardNumber
import blackjack.domain.BlackJackCardShape
import blackjack.domain.BlackJackCardsBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackCardTest {
    @Test
    fun `카드는 에이스(1,11)부터 2~10 JACK(10) QUEEN(10) KING(10)이 있다`() {
        val blackJackCards = BlackJackCardsBuilder().build()
        assertThat(blackJackCards.get(BlackJackCardShape.HEART, BlackJackCardNumber.ACE).number.values).containsExactly(1, 11)
        assertThat(blackJackCards.get(BlackJackCardShape.HEART, BlackJackCardNumber.ACE).shape.shape).isEqualTo("HEART")
    }
}
