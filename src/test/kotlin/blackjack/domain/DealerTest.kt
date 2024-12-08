package blackjack.domain

import blackjack.fixtures.createCard
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class DealerTest : StringSpec({
    "딜러는 카드를 가진다" {
        val dealer = Dealer.create()
        dealer.cards shouldNotBe null
    }

    "딜러는 첫 2장의 합이 16이하면 반드시 카드를 받아야 한다" {
        val dealer = Dealer(Cards(listOf(createCard("10"), createCard("6"))))

        dealer.canHit() shouldBe true
    }
})
