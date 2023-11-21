package blackjack_dealer

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeLessThan

class DealerTest : BehaviorSpec({
    Given("딜러는 처음 두장을 받는다") {
        When("받은 두장의 합계가 16 이하면 1장을 더 받는다") {

            val lessThanScoreExpected = 16
            val cardCountExpected = 3
            dealer.gamerCards.getCurrentScore() shouldBeLessThan lessThanScoreExpected
            dealer.gamerCards.count() shouldBe expected
        }

        When("받은 두장의 합계가 17 이상이면 추가로 받을 수 없다") {
            
            val moreThanScoreExpected = 17
            val cardCountExpected = 2
            dealer.gamerCards.getCurrentScore() shouldBeMoreThan moreThanScoreExpected
            dealer.gamerCards.count() shouldBe cardCountExpected
        }
    }
})
