package blackjack

import blackjack.domain.BlackJackCardNumber
import blackjack.domain.BlackJackCardShape
import blackjack.domain.BlackJackCardsMap
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class BlackJackCardTest {
    @Test
    fun `카드는 에이스(1,11)부터 2~10 JACK(10) QUEEN(10) KING(10)이 있다`() {
        val blackJackCard = BlackJackCardsMap().get(BlackJackCardShape.HEART, BlackJackCardNumber.ACE)
        assertThat(blackJackCard.number.values).containsExactly(1, 11)
        assertThat(blackJackCard.shape.shape).isEqualTo("HEART")
    }

    @Test
    fun `중복된 카드는 지급될수 없다`() {
        val blackJackCardsMap = BlackJackCardsMap()
        val blackJackCard = blackJackCardsMap.get(BlackJackCardShape.HEART, BlackJackCardNumber.ACE)
        assertThatThrownBy { blackJackCardsMap.get(BlackJackCardShape.HEART, BlackJackCardNumber.ACE) }
            .isInstanceOf(IllegalArgumentException::class.java).hasMessage("카드가 이미 사용중이에요")
    }
}
