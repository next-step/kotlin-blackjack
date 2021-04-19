package blackjack.domain.state.started

import blackjack.domain.card.Card
import blackjack.domain.card.CardShape
import blackjack.domain.card.CardType
import blackjack.domain.card.Cards
import blackjack.domain.state.started.Running.Hit
import blackjack.domain.state.started.finished.Bust
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class HitTest {

    @Test
    fun `카드를 받았는데 21이 넘은 경우`() {
        val playerCards = Cards(listOf(Card(CardShape.SPADE, CardType.QUEEN), Card(CardShape.SPADE, CardType.JACK)))
        val playerState = Hit(playerCards)

        val nextState = playerState.takeCard(Card(CardShape.HEART, CardType.QUEEN))

        assertThat(nextState).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `카드를 받았는데 21이 넘지 않은 경우`() {
        val playerCards = Cards(listOf(Card(CardShape.SPADE, CardType.EIGHT), Card(CardShape.SPADE, CardType.JACK)))
        val playerState = Hit(playerCards)

        val nextState = playerState.takeCard(Card(CardShape.HEART, CardType.TWO))

        assertThat(nextState).isInstanceOf(Hit::class.java)
    }
}
