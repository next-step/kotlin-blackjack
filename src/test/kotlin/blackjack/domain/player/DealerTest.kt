package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Score
import blackjack.domain.card.Suit
import blackjack.domain.card.state.StateFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DealerTest {

    private val card1 = Card(Suit.CLUB, Denomination.ACE)
    private val card2 = Card(Suit.CLUB, Denomination.TWO)

    @Test
    fun `딜러의 점수 계산`() {
        val state = StateFactory.create(card1, card2)
        val dealer = Dealer(state)

        assertThat(dealer.score).isEqualTo(Score.of(13))
    }

    @Test
    fun `딜러가 한장의 카드를 받을 때 점수 계산 ace(11) + two(2) = 13, 13 + SIX(6) = 19`() {
        val state = StateFactory.create(card1, card2)
        val dealer = Dealer(state)
        assertThat(dealer.score).isEqualTo(Score.of(13))

        val card = Card(Suit.SPADE, Denomination.SIX)
        dealer.take(card)
        assertThat(dealer.score).isEqualTo(Score.of(19))
    }

    @Test
    fun `딜러는 16점 미만이면 반듯이 카드를 받아야 한다 11 + 2 = 13`() {
        val state = StateFactory.create(card1, card2)
        val dealer = Dealer(state)

        assertThat(dealer.isMustTakeCard()).isTrue()
    }

    @Test
    fun `딜러는 17점 이상이면 카드를 받지 않는다 11 + 2 = 13`() {
        val ten = Card(Suit.CLUB, Denomination.TEN)
        val seven = Card(Suit.CLUB, Denomination.SEVEN)

        val state = StateFactory.create(ten, seven)
        val dealer = Dealer(state)

        assertThat(dealer.isMustTakeCard()).isFalse()
    }
}
