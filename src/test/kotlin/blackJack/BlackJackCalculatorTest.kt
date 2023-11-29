package blackJack

import BlackJackCalculator
import card.CardRank
import card.PlayingCard
import card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackCalculatorTest {

    @Test
    fun `카드 리스트를 받는다, 카드의 합을 요청할 때, 승리에 유리한 합을 반환한다`() {
        // given : 카드 리스트를 받는다.
        val cardList = listOf(PlayingCard(Suit.HEART, CardRank.ACE), PlayingCard(Suit.HEART, CardRank.ACE))

        // when : 카드 합을 요청한다.
        val actual: Int = BlackJackCalculator.calculate(cardList)

        // then : 승리에 유리한 합을 반환한다.
        assertThat(actual).isEqualTo(12)
    }
}
