package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DealerTest : BehaviorSpec({
    given("트럼프 카드가 주어지면") {
        val trumpCard = TrumpCard.init()
        When("딜러에게 초기 카드를 주어질때") {
            val dealer = Dealer(trumpCard.firstCardDraw())
            Then("딜러의 카드는 2장이다.") {
                dealer.cardSet.size shouldBe 2
            }
        }
    }
})
