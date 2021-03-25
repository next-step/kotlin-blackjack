package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Denomination
import blackjack.domain.MatchResult
import blackjack.domain.card.Score
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerTest {

    private val card1 = Card(Suit.CLUB, Denomination.ACE)
    private val card2 = Card(Suit.CLUB, Denomination.TWO)

    @Test
    fun `플레이어의 점수 계산`() {
        val cards = Cards(card1, card2)
        val player = Player(Name("test"), cards)

        assertThat(player.score).isEqualTo(Score.of(13))
    }

    @Test
    fun `플레이어가 한장의 카드를 받을 때 점수 계산 ace(11) + two(2) = 13, 13 + SIX(6) = 19`() {
        val cards = Cards(card1, card2)
        val player = Player(Name("test"), cards)
        assertThat(player.score).isEqualTo(Score.of(13))

        val card = Card(Suit.SPADE, Denomination.SIX)
        player.take(card)
        assertThat(player.score).isEqualTo(Score.of(19))
    }

    @Test
    fun `플레이어가 딜러보다 점수가 높을 경우`() {
        val dealer = Dealer(Cards((card1)))
        val cards = Cards(card1, card2)
        val player = Player(Name("test"), cards)

        assertThat(player.match(dealer)).isEqualTo(MatchResult.WIN)
    }

    @Test
    fun `플레이어가 딜러보다 점수가 낮을 경우`() {
        val dealer = Dealer(Cards(card1, card2))
        val cards = Cards(card1)
        val player = Player(Name("test"), cards)

        assertThat(player.match(dealer)).isEqualTo(MatchResult.LOSE)
    }

    @Test
    fun `플레이어가 딜러보다 점수가 같을 경우`() {
        val dealer = Dealer(Cards((card1)))
        val cards = Cards(card1)
        val player = Player(Name("test"), cards)

        assertThat(player.match(dealer)).isEqualTo(MatchResult.DRAW)
    }
}
