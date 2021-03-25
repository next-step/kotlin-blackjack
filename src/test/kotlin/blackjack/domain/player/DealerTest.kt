package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Denomination
import blackjack.domain.Score
import blackjack.domain.Suit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class DealerTest {

    private val card1 = Card(Suit.CLUB, Denomination.ACE)
    private val card2 = Card(Suit.CLUB, Denomination.TWO)

    @Test
    fun `딜러의 점수 계산`() {
        val cards = Cards(card1, card2)
        val dealer = Dealer(cards)

        Assertions.assertThat(dealer.score).isEqualTo(Score.of(13))
    }

    @Test
    fun `딜러가 한장의 카드를 받을 때 점수 계산 ace(11) + two(2) = 13, 13 + SIX(6) = 19`() {
        val cards = Cards(card1, card2)
        val dealer = Dealer(cards)
        Assertions.assertThat(dealer.score).isEqualTo(Score.of(13))

        val card = Card(Suit.SPADE, Denomination.SIX)
        dealer.take(card)
        Assertions.assertThat(dealer.score).isEqualTo(Score.of(19))
    }
}
