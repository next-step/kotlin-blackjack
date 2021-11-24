package blackjack.domain.player

import blackjack.DealerHand
import blackjack.PlayerHand
import blackjack.domain.card.Card
import blackjack.domain.card.Score.Companion.BLACK_JACK_SCORE
import blackjack.domain.card.Symbol
import blackjack.domain.card.Type
import blackjack.domain.game.BlackJack
import blackjack.domain.game.Bust
import blackjack.domain.game.Money
import blackjack.domain.game.Stay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class PlayerTest {

    @Nested
    inner class PlayerHand {

        @Test
        fun `PlayerHand를 hit했을 때 블랙잭 점수 미만이면 계속 hit 할 수 있다`() {
            val one = Card(Symbol.ACE, Type.CLUB)
            val hand = PlayerHand(BLACK_JACK_SCORE.value - 2)

            val result = hand.hit(one)

            assertThat(result.canHit()).isTrue
        }

        @Test
        fun `PlayerHand를 hit했을 때 블랙잭이라면 BlackJack이다`() {
            val eleven = Card(Symbol.ACE, Type.CLUB)
            val hand = PlayerHand(10)

            val result = hand.hit(eleven)

            assertThat(result).isInstanceOf(BlackJack::class.java)
            assertThat(result.canHit()).isFalse
        }

        @Test
        fun `PlayerHand를 hit했을 때 블랙잭 점수라면 Stay이다`() {
            val one = Card(Symbol.ACE, Type.CLUB)
            val hand = PlayerHand(BLACK_JACK_SCORE.value - 1)

            val result = hand.hit(one)

            assertThat(result).isInstanceOf(Stay::class.java)
            assertThat(result.canHit()).isFalse
        }

        @Test
        fun `PlayerHand를 hit했을 때 점수가 블랙잭 점수보다 크다면 Bust이다`() {
            val two = Card(Symbol.TWO, Type.CLUB)
            val hand = PlayerHand(BLACK_JACK_SCORE.value - 1)

            val result = hand.hit(two)

            assertThat(result).isInstanceOf(Bust::class.java)
            assertThat(result.canHit()).isFalse
        }
    }

    @Nested
    inner class Profit {

        private val name = PlayerName("aaj")
        private val bustScore = BLACK_JACK_SCORE.value + 1

        @Test
        fun `플레이어가 bust라면 딜러 결과와는 상관없이 플레이어는 배팅한 금액을 잃는다`() {
            val dealer = Dealer(DealerHand(bustScore))
            val player = Player(name, Money(1000), PlayerHand(bustScore))

            val result = player.calculateProfitBy(dealer)

            assertThat(result.value).isEqualTo(-1000)
        }

        @Test
        fun `딜러만 bust라면 플레이어는 배팅한 금액만큼 얻는다`() {
            val dealer = Dealer(DealerHand(bustScore))
            val player = Player(name, Money(1000), PlayerHand(2))

            val result = player.calculateProfitBy(dealer)

            assertThat(result.value).isEqualTo(1000)
        }

        @Test
        fun `플레이어만 blackjack이라면 플레이어는 배팅한 금액의 반을 얻는다`() {
            val dealer = Dealer(DealerHand(BLACK_JACK_SCORE.value, isBlackJack = false))
            val player = Player(name, Money(1000), PlayerHand(BLACK_JACK_SCORE.value, isBlackJack = true))

            val result = player.calculateProfitBy(dealer)

            assertThat(result.value).isEqualTo(500)
        }

        @Test
        fun `딜러만 blackjack이라면 플레이어는 배팅한 금액을 잃는다`() {
            val dealer = Dealer(DealerHand(BLACK_JACK_SCORE.value, isBlackJack = true))
            val player = Player(name, Money(1000), PlayerHand(BLACK_JACK_SCORE.value, isBlackJack = false))

            val result = player.calculateProfitBy(dealer)

            assertThat(result.value).isEqualTo(-1000)
        }

        @Test
        fun `플레이어와 딜러 모두 blackjack이라면 플레이어의 수익은 없다`() {
            val dealer = Dealer(DealerHand(BLACK_JACK_SCORE.value, isBlackJack = true))
            val player = Player(name, Money(1000), PlayerHand(BLACK_JACK_SCORE.value, isBlackJack = true))

            val result = player.calculateProfitBy(dealer)

            assertThat(result.value).isEqualTo(0)
        }

        @Test
        fun `딜러와 플레이어가 bust가 아닐 때 플레이어의 점수가 더 높다면 플레이어는 배팅한 금액을 얻는다`() {
            val dealer = Dealer(DealerHand(18))
            val player = Player(name, Money(1000), PlayerHand(19))

            val result = player.calculateProfitBy(dealer)

            assertThat(result.value).isEqualTo(1000)
        }

        @Test
        fun `딜러와 플레이어가 bust가 아닐 때 플레이어의 점수가 같다면 플레이어의 수익은 없다`() {
            val dealer = Dealer(DealerHand(18))
            val player = Player(name, Money(1000), PlayerHand(18))

            val result = player.calculateProfitBy(dealer)

            assertThat(result.value).isEqualTo(0)
        }

        @Test
        fun `딜러와 플레이어가 bust가 아닐 때 플레이어의 점수가 더 낮다면 플레이어는 배팅한 금액을 잃는다`() {
            val dealer = Dealer(DealerHand(18))
            val player = Player(name, Money(1000), PlayerHand(17))

            val result = player.calculateProfitBy(dealer)

            assertThat(result.value).isEqualTo(-1000)
        }
    }
}
