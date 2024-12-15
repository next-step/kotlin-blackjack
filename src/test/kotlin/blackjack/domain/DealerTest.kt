package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

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
})
