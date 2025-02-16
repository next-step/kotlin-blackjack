package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal

class DealerTest : FreeSpec({
    "가진 카드의 합이 17점 이상이면 카드를 받을 수 없다" {
        val dealer = Dealer()
        dealer.receive(Card(Suit.HEARTS, Rank.TEN), Card(Suit.HEARTS, Rank.SEVEN))

        dealer.canNotReceiveCard() shouldBe true
    }

    "가진 카드의 합이 16점 이하면 카드를 받을 수 있다" {
        val dealer = Dealer()
        dealer.receive(Card(Suit.HEARTS, Rank.TEN), Card(Suit.HEARTS, Rank.SIX))

        dealer.canNotReceiveCard() shouldBe false
    }

    "게임 결과인 BlackjackResult를 만든다" {
        val dealer = Dealer()
        dealer.receive(Card(Suit.DIAMONDS, Rank.ACE), Card(Suit.DIAMONDS, Rank.TEN))  // 21

        val gambler1 = Gambler("kim")
        gambler1.receive(Card(Suit.CLUBS, Rank.TWO), Card(Suit.CLUBS, Rank.THREE))  // 5
        gambler1.placeBet(1000)

        val gambler2 = Gambler("lee")
        gambler2.receive(Card(Suit.CLUBS, Rank.ACE), Card(Suit.CLUBS, Rank.TEN))  // 21
        gambler2.placeBet(1000)

        val gambler3 = Gambler("lee")
        gambler3.receive(Card(Suit.HEARTS, Rank.TWO), Card(Suit.HEARTS, Rank.THREE))  // 5
        gambler3.placeBet(1000)

        val blackjackResults = dealer.determineResult(
            listOf(gambler1, gambler2, gambler3)
        )

        blackjackResults.dealerResult.profit shouldBe BigDecimal("1000")

        val gamblerResults = blackjackResults.gamblerResults
        gamblerResults[0].profit shouldBe BigDecimal("-1000")
        gamblerResults[1].profit shouldBe BigDecimal("1000")
        gamblerResults[2].profit shouldBe BigDecimal("-1000")
    }
})
