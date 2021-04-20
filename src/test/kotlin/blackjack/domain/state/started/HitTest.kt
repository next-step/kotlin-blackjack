package blackjack.domain.state.started

import blackjack.domain.card.CardType
import blackjack.domain.cardQueen
import blackjack.domain.cardTwo
import blackjack.domain.cards
import blackjack.domain.state.started.run.Hit
import blackjack.domain.state.started.finished.Bust
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class HitTest {

    @Test
    fun `카드를 받았는데 21이 넘은 경우`() {
        val playerCards = cards(CardType.QUEEN, CardType.JACK)
        val playerState = Hit(playerCards)

        val nextState = playerState.takeCard(cardQueen)

        assertThat(nextState).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `카드를 받았는데 21이 넘지 않은 경우`() {
        val playerCards = cards(CardType.EIGHT, CardType.JACK)
        val playerState = Hit(playerCards)

        val nextState = playerState.takeCard(cardTwo)

        assertThat(nextState).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `카드 size 테스트`() {
        val hit = Hit(cards(CardType.ACE, CardType.JACK))
        assertThat(hit.cardSize).isEqualTo(2)

        hit.takeCard(cardTwo)
        assertThat(hit.cardSize).isEqualTo(3)
    }
}
