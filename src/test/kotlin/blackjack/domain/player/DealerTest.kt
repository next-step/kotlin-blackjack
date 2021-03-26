package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Score
import blackjack.domain.card.Suit
import blackjack.domain.card.state.StateFactory
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class DealerTest {

    private val card1 = Card(Suit.CLUB, Denomination.ACE)
    private val card2 = Card(Suit.CLUB, Denomination.TWO)

    @Test
    fun `딜러의 점수 계산`() {
        val state = StateFactory.create(card1, card2)
        val dealer = Dealer(state)

        Assertions.assertThat(dealer.score).isEqualTo(Score.of(13))
    }

    @Test
    fun `딜러가 한장의 카드를 받을 때 점수 계산 ace(11) + two(2) = 13, 13 + SIX(6) = 19`() {
        val state = StateFactory.create(card1, card2)
        val dealer = Dealer(state)
        Assertions.assertThat(dealer.score).isEqualTo(Score.of(13))

        val card = Card(Suit.SPADE, Denomination.SIX)
        dealer.take(card)
        Assertions.assertThat(dealer.score).isEqualTo(Score.of(19))
    }
}
