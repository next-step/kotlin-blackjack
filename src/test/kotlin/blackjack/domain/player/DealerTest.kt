package blackjack.domain.player

import blackjack.fixtures.createCard
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DealerTest : BehaviorSpec({
    Given("딜러 히트하는 경우") {
        val dealer = Dealer()

        When("히트하면") {
            dealer.hit(createCard("J"))

            Then("카드를 추가로 갖는다") {
                dealer.hand.cards.count() shouldBe 1
            }
        }
    }

    Given("딜러 핸드가 17점 이상인 경우") {
        val dealer = Dealer()
        dealer.hit(createCard("J"))
        dealer.hit(createCard("Q"))

        When("히트 가능 여부 질의하면") {
            Then("결과 false 이다") {
                dealer.canHit() shouldBe false
            }
        }
    }

    Given("딜러 핸드가 17점 미만인 경우") {
        val dealer = Dealer()
        dealer.hit(createCard("J"))

        When("히트 가능 여부 질의하면") {
            Then("결과는 true 이다") {
                dealer.canHit() shouldBe true
            }
        }
    }
})
