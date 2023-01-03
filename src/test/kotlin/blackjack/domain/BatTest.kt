package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Shape
import blackjack.domain.player.Dealer
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.Hit
import blackjack.domain.state.Stay
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class BatTest {
    @Test
    fun `Blackjack 이면 베팅 금액의 일점오배를 딜러에게 받는다`() {
        val blackjack = Blackjack(Hand(Card(Shape.CLUB, Denomination.ACE), Card(Shape.CLUB, Denomination.TEN)))
        val hit = Hit(Hand(Card(Shape.CLUB, Denomination.TEN), Card(Shape.CLUB, Denomination.SEVEN)))

        val dealer = Dealer(hit)

        val bat = Bat(10000.0)

        bat.profit(blackjack, dealer) shouldBe 15000.0
    }

    @Test
    fun `딜러와 플레이어 둘다 Blackjack이면 베팅금액을 돌려받는다`() {
        val blackjack = Blackjack(Hand(Card(Shape.CLUB, Denomination.ACE), Card(Shape.CLUB, Denomination.TEN)))

        val dealer = Dealer(blackjack)

        val bat = Bat(10000.0)

        bat.profit(blackjack, dealer) shouldBe 0.0
    }

    @Test
    fun `딜러가 Blackjack 이면 베팅금액을 잃는다`() {
        val blackjack = Blackjack(Hand(Card(Shape.CLUB, Denomination.ACE), Card(Shape.CLUB, Denomination.TEN)))
        val stay = Stay(Hand(Card(Shape.CLUB, Denomination.TEN), Card(Shape.CLUB, Denomination.SEVEN)))

        val dealer = Dealer(blackjack)

        val bat = Bat(10000.0)

        bat.profit(stay, dealer) shouldBe -10000.0
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

        val dealer = Dealer(bust)

        val bat = Bat(10000.0)

        bat.profit(hit, dealer) shouldBe 10000.0
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

        val dealer = Dealer(bust)

        val bat = Bat(10000.0)

        bat.profit(bust, dealer) shouldBe 10000.0
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

        val dealer = Dealer(hit)

        val bat = Bat(10000.0)

        bat.profit(bust, dealer) shouldBe -10000.0
    }

    @Test
    fun `플레이어와 딜러의 점수가 같으면 플레이어가 베팅금액을 받는다`() {
        val stay = Stay(Hand(Card(Shape.CLUB, Denomination.TEN), Card(Shape.CLUB, Denomination.SEVEN)))

        val dealer = Dealer(stay)

        val bat = Bat(10000.0)

        bat.profit(stay, dealer) shouldBe 10000.0
    }

    @Test
    fun `플레이어가 딜러보다 점수가 높으면 플레이어가 베팅 금액을 받는다`() {
        val seventeen = Stay(Hand(Card(Shape.CLUB, Denomination.TEN), Card(Shape.CLUB, Denomination.SEVEN)))
        val sixteen = Stay(Hand(Card(Shape.CLUB, Denomination.TEN), Card(Shape.CLUB, Denomination.SIX)))

        val dealer = Dealer(sixteen)

        val bat = Bat(10000.0)

        bat.profit(seventeen, dealer) shouldBe 10000.0
    }

    @Test
    fun `딜러가 플레이어보다 점수가 높으면 베팅금액을 잃는다`() {
        val seventeen = Stay(Hand(Card(Shape.CLUB, Denomination.TEN), Card(Shape.CLUB, Denomination.SEVEN)))
        val sixteen = Stay(Hand(Card(Shape.CLUB, Denomination.TEN), Card(Shape.CLUB, Denomination.SIX)))

        val dealer = Dealer(seventeen)

        val bat = Bat(10000.0)

        bat.profit(sixteen, dealer) shouldBe -10000.0
    }
}
