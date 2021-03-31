package blackjack.domain.player

import blackjack.domain.Money
import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Score
import blackjack.domain.card.Suit
import blackjack.domain.card.state.StateFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class PlayerTest {

    private val ace = Card(Suit.CLUB, Denomination.ACE)
    private val two = Card(Suit.CLUB, Denomination.TWO)
    private val betting = Money(10000)

    @Test
    fun `플레이어의 점수 계산`() {
        val state = StateFactory.create(ace, two)
        val player = Player(Name("test"), state, betting)

        assertThat(player.score).isEqualTo(Score.of(13))
    }

    @Test
    fun `플레이어가 한장의 카드를 받을 때 점수 계산 ace(11) + two(2) = 13, 13 + SIX(6) = 19`() {
        val state = StateFactory.create(ace, two)
        val player = Player(Name("test"), state, betting)
        assertThat(player.score).isEqualTo(Score.of(13))

        val card = Card(Suit.SPADE, Denomination.SIX)
        player.take(card)
        assertThat(player.score).isEqualTo(Score.of(19))
    }

    @Nested
    @DisplayName("플레이어가 이겼을 때")
    inner class WhenPlayerWin {
        private val ace = Card(Suit.CLUB, Denomination.ACE)
        private val two = Card(Suit.CLUB, Denomination.TWO)
        private val ten = Card(Suit.CLUB, Denomination.TEN)

        @Test
        fun `딜러가 bust 인 경우 베팅 금액 만큼 받는다`() {
            val dealerState = StateFactory.create(ten, ten)
            val playerState = StateFactory.create(ten, two)

            val dealer = Dealer(dealerState)
            val money = Money(10000)
            val player = Player(Name("test"), playerState, money)
            dealer.take(two)

            assertThat(player.match(dealer)).isEqualTo(Money(10000))
        }

        @Test
        fun `stay 인 경우 베팅 금액 만큼 받는다 `() {
            val dealerState = StateFactory.create(ace, two)
            val playerState = StateFactory.create(ace, two)

            val dealer = Dealer(dealerState)
            val money = Money(10000)
            val player = Player(Name("test"), playerState, money)
            player.take(two)
            player.stay()

            assertThat(player.match(dealer)).isEqualTo(Money(10000))
        }

        @Test
        fun `blackjack 인 경우 베팅 금액 x 1,5 만큼 받는다 `() {
            val dealerState = StateFactory.create(ace, two)
            val playerState = StateFactory.create(ace, ten)

            val dealer = Dealer(dealerState)
            val betting = Money(10000)
            val player = Player(Name("test"), playerState, betting)

            assertThat(player.match(dealer)).isEqualTo(Money(10000) * 1.5)
        }
    }

    @Test
    fun `플레이어가 딜러보다 점수가 낮을 경우 배팅 금액 만큼 잃는`() {
        val dealerState = StateFactory.create(ace, two)
        val playerState = StateFactory.create(ace, two)

        val dealer = Dealer(dealerState)
        val player = Player(Name("test"), playerState, betting)
        dealer.take(two)

        assertThat(player.match(dealer)).isEqualTo(betting * -1.0)
    }

    @Nested
    @DisplayName("비긴 경우 수익금은 없다")
    inner class WhenDraw {
        private val ace = Card(Suit.CLUB, Denomination.ACE)
        private val ten = Card(Suit.CLUB, Denomination.TEN)

        @Test
        fun `플레이어가 딜러보다 점수가 같을 경우`() {
            val dealerState = StateFactory.create(ace, ten)
            val playerState = StateFactory.create(ace, ten)

            val dealer = Dealer(dealerState)
            val player = Player(Name("test"), playerState, betting)

            assertThat(player.match(dealer)).isEqualTo(Money.ZERO)
        }

        @Test
        fun `플레이어, 딜러 모두 블랙잭인 경우`() {
            val dealerState = StateFactory.create(ace, ten)
            val playerState = StateFactory.create(ace, ten)

            val dealer = Dealer(dealerState)
            val player = Player(Name("test"), playerState, betting)

            assertThat(player.match(dealer)).isEqualTo(Money.ZERO)
        }
    }
}
