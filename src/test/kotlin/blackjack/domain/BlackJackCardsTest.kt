package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class BlackJackCardsTest {
    @Test
    fun `카드는 에이스(1,11)부터 2~10 JACK(10) QUEEN(10) KING(10)이 있다`() {
        val blackJackCard =
            BlackJackCards(mutableListOf(BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.ACE))).draw()
        assertThat(blackJackCard.number.values).containsExactly(1, 11)
        assertThat(blackJackCard.shape).isEqualTo(BlackJackCardShape.HEART)
    }

    @Test
    fun `중복된 카드는 지급될수 없다`() {
        val blackJackCards =
            BlackJackCards(mutableListOf(BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.ACE)))
        blackJackCards.draw()
        assertThatThrownBy { blackJackCards.draw() }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("카드가 없어요")
    }
}
