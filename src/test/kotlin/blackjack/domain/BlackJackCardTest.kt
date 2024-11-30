package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
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
        val blackJackCard1 = blackJackCardsMap.get(BlackJackCardShape.HEART, BlackJackCardNumber.ACE)
        val blackJackCard2 = blackJackCardsMap.get(BlackJackCardShape.HEART, BlackJackCardNumber.ACE)

        assertThat(blackJackCard1.shape).isEqualTo(BlackJackCardShape.HEART)
        assertThat(blackJackCard1.number).isEqualTo(BlackJackCardNumber.ACE)
        assertThat(blackJackCard2.shape).isNotEqualTo(BlackJackCardShape.HEART)
        assertThat(blackJackCard2.number).isNotEqualTo(BlackJackCardNumber.ACE)
    }
}
