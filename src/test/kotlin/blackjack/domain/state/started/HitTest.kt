package blackjack.domain.state.started

import blackjack.domain.Card
import blackjack.domain.CardShape
import blackjack.domain.CardType
import blackjack.domain.Cards
import blackjack.domain.state.started.finished.Bust
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class HitTest {

    @Test
    fun `카드를 받았는데 21이 넘은 경우`() {
        val playerCards = Cards(listOf(Card(CardShape.SPADE, CardType.QUEEN), Card(CardShape.SPADE, CardType.JACK)))
        val playerState = Hit(playerCards)

        val nextState = playerState.takeCard(Card(CardShape.HEART, CardType.QUEEN))

        Assertions.assertThat(nextState).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `카드를 받았는데 21이 넘지 않은 경우`() {
        val playerCards = Cards(listOf(Card(CardShape.SPADE, CardType.EIGHT), Card(CardShape.SPADE, CardType.JACK)))
        val playerState = Hit(playerCards)

        val nextState = playerState.takeCard(Card(CardShape.HEART, CardType.TWO))

        Assertions.assertThat(nextState).isInstanceOf(Hit::class.java)
    }
}
