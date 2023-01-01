package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Shape
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.Hit
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class BetTest {
    @Test
    fun `Blackjack 이면 베팅 금액의 절반을 더하여 딜러에게 받는다`() {
        val blackjack = Blackjack(Hand(Card(Shape.CLUB, Denomination.ACE), Card(Shape.CLUB, Denomination.TEN)))
        val hit = Hit(Hand(Card(Shape.CLUB, Denomination.TEN), Card(Shape.CLUB, Denomination.SEVEN)))

        val player = Player("player", blackjack)
        val dealer = Dealer(hit)

        val bet = Bet(player, 10000.0)

        bet.profit(dealer) shouldBe 15000.0
    }

    @Test
    fun `딜러와 플레이어 둘다 Blackjack이면 베팅금액을 돌려받는다`() {
        val blackjack = Blackjack(Hand(Card(Shape.CLUB, Denomination.ACE), Card(Shape.CLUB, Denomination.TEN)))

        val player = Player("player", blackjack)
        val dealer = Dealer(blackjack)

        val bet = Bet(player, 10000.0)

        bet.profit(dealer) shouldBe 0.0
    }

    @Test
    fun `딜러가 Blackjack 이면 베팅금액을 잃는다`() {
        val blackjack = Blackjack(Hand(Card(Shape.CLUB, Denomination.ACE), Card(Shape.CLUB, Denomination.TEN)))
        val hit = Hit(Hand(Card(Shape.CLUB, Denomination.TEN), Card(Shape.CLUB, Denomination.SEVEN)))

        val player = Player("player", hit)
        val dealer = Dealer(blackjack)

        val bet = Bet(player, 10000.0)

        bet.profit(dealer) shouldBe -10000.0
    }

    @Test
    fun `딜러가 Bust이면 상관 없이 베팅금액을 받는다`() {
        val bust = Bust(
            Hand(
                Card(Shape.CLUB, Denomination.TEN),
                Card(Shape.CLUB, Denomination.TEN),
                Card(Shape.CLUB, Denomination.TEN)
            )
        )

        val hit = Hit(Hand(Card(Shape.CLUB, Denomination.TEN), Card(Shape.CLUB, Denomination.SEVEN)))

        val player = Player("player", hit)
        val dealer = Dealer(bust)

        val bet = Bet(player, 10000.0)

        bet.profit(dealer) shouldBe 10000.0
    }

    @Test
    fun `플레이어와 딜러가 둘 다 Bust이면 베팅금액을 받는다`() {
        val bust = Bust(
            Hand(
                Card(Shape.CLUB, Denomination.TEN),
                Card(Shape.CLUB, Denomination.TEN),
                Card(Shape.CLUB, Denomination.TEN)
            )
        )

        val player = Player("player", bust)
        val dealer = Dealer(bust)

        val bet = Bet(player, 10000.0)

        bet.profit(dealer) shouldBe 10000.0
    }

    @Test
    fun `플레이어가 Bust이면 베팅금액을 잃는다`() {
        val bust = Bust(
            Hand(
                Card(Shape.CLUB, Denomination.TEN),
                Card(Shape.CLUB, Denomination.ACE),
                Card(Shape.CLUB, Denomination.TEN)
            )
        )
        val hit = Hit(Hand(Card(Shape.CLUB, Denomination.TEN), Card(Shape.CLUB, Denomination.SEVEN)))

        val player = Player("player", bust)
        val dealer = Dealer(hit)

        val bet = Bet(player, 10000.0)

        bet.profit(dealer) shouldBe -10000.0
    }

    @Test
    fun `플레이어와 딜러의 점수가 같으면 플레이어가 베팅금액을 받는다`() {
        val hit = Hit(Hand(Card(Shape.CLUB, Denomination.TEN), Card(Shape.CLUB, Denomination.SEVEN)))

        val player = Player("player", hit)
        val dealer = Dealer(hit)

        val bet = Bet(player, 10000.0)

        bet.profit(dealer) shouldBe 10000.0
    }

    @Test
    fun `플레이어가 딜러보다 점수가 높으면 플레이어가 베팅 금액을 받는다`() {
        val seventeen = Hit(Hand(Card(Shape.CLUB, Denomination.TEN), Card(Shape.CLUB, Denomination.SEVEN)))
        val sixteen = Hit(Hand(Card(Shape.CLUB, Denomination.TEN), Card(Shape.CLUB, Denomination.SIX)))

        val player = Player("player", seventeen)
        val dealer = Dealer(sixteen)

        val bet = Bet(player, 10000.0)

        bet.profit(dealer) shouldBe 10000.0
    }

    @Test
    fun `딜러가 플레이어보다 점수가 높으면 베팅금액을 잃는다`() {
        val seventeen = Hit(Hand(Card(Shape.CLUB, Denomination.TEN), Card(Shape.CLUB, Denomination.SEVEN)))
        val sixteen = Hit(Hand(Card(Shape.CLUB, Denomination.TEN), Card(Shape.CLUB, Denomination.SIX)))

        val player = Player("player", sixteen)
        val dealer = Dealer(seventeen)

        val bet = Bet(player, 10000.0)

        bet.profit(dealer) shouldBe -10000.0
    }
}
