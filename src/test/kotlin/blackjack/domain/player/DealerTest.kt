package blackjack.domain.player

import blackjack.DealerHand
import blackjack.PlayerHand
import blackjack.domain.card.Card
import blackjack.domain.card.Score
import blackjack.domain.card.Score.Companion.BLACK_JACK_SCORE
import blackjack.domain.card.Symbol
import blackjack.domain.card.Type
import blackjack.domain.game.BlackJack
import blackjack.domain.game.Bust
import blackjack.domain.game.Stay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class DealerTest {

    @Test
    fun `DealerHand hit했을 때 점수가 16 이하면 계속 hit 할 수 있다`() {
        val one = Card(Symbol.ACE, Type.CLUB)
        val hand = DealerHand(DEALER_HIT_MAX - 1)

        val result = hand.hit(one)

        assertThat(result.canHit()).isTrue
    }

    @Test
    fun `DealerHand hit했을 때 블랙잭이라면 BlackJack이다`() {
        val eleven = Card(Symbol.ACE, Type.CLUB)
        val hand = DealerHand(10)

        val result = hand.hit(eleven)

        assertThat(result).isInstanceOf(BlackJack::class.java)
        assertThat(result.canHit()).isFalse
    }

    @Test
    fun `DealerHand hit했을 때 17 이상이면 Stay이다`() {
        val one = Card(Symbol.ACE, Type.CLUB)
        val hand = DealerHand(DEALER_HIT_MAX)

        val result = hand.hit(one)

        assertThat(result).isInstanceOf(Stay::class.java)
        assertThat(result.canHit()).isFalse
    }

    @Test
    fun `DealerHand hit했을 때 점수가 블랙잭 점수보다 크다면 Bust이다`() {
        val two = Card(Symbol.SIX, Type.CLUB)
        val hand = DealerHand(DEALER_HIT_MAX)

        val result = hand.hit(two)

        assertThat(result).isInstanceOf(Bust::class.java)
        assertThat(result.canHit()).isFalse
    }
}
