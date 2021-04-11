package blackjack.domain.state.notstarted

import blackjack.domain.Card
import blackjack.domain.CardShape
import blackjack.domain.CardType
import blackjack.domain.Cards
import blackjack.domain.state.started.Hit
import blackjack.domain.state.started.finished.BlackJack
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class NotStartedTest {

    @Test
    fun `takeFirstTwoCards 블랙잭인 경우`() {
        val state = NotStarted()
        val cards = Cards(listOf(Card(CardShape.SPADE, CardType.QUEEN), Card(CardShape.SPADE, CardType.ACE)))
        val nextState = state.takeFirstTwoCards(cards)

        Assertions.assertThat(nextState).isInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `takeFirstTwoCards 블랙잭이 아닌 경우`() {
        val state = NotStarted()
        val cards = Cards(listOf(Card(CardShape.SPADE, CardType.QUEEN), Card(CardShape.SPADE, CardType.TWO)))
        val nextState = state.takeFirstTwoCards(cards)

        Assertions.assertThat(nextState).isInstanceOf(Hit::class.java)
    }
}
