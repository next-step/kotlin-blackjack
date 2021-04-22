package blackjack.domain.state.started

import blackjack.domain.card.CardType
import blackjack.domain.card.Cards
import blackjack.domain.cardQueen
import blackjack.domain.cardTwo
import blackjack.domain.cards
import blackjack.domain.state.notstarted.NotStarted
import blackjack.domain.state.started.finished.BlackJack
import blackjack.domain.state.started.run.Hit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class StartedTest {

    @Test
    fun `takeCard 첫 번째 카드인 경우`() {
        val state = NotStarted()
        val nextState = state.takeCard(cardTwo)

        assertThat(nextState).isInstanceOf(NotStarted::class.java)
    }

    @Test
    fun `takeFirstTwoCards 두 번째 카드인 경우`() {
        val state = NotStarted(cards(CardType.FIVE))
        val nextState = state.takeCard(cardTwo)

        assertThat(nextState).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `takeFirstTwoCards 두 번째 카드를 받았는데 BlackJack인 경우`() {
        val state = NotStarted(cards(CardType.ACE))
        val nextState = state.takeCard(cardQueen)

        assertThat(nextState).isInstanceOf(BlackJack::class.java)
    }
}
