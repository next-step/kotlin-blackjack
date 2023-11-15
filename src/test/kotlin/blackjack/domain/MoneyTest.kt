package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MoneyTest : BehaviorSpec({
    given("Money가 주어진다면") {
        val money = Money(1000)
        When("Money가 주어진다면") {
            then("Money는 1000이다.") {
                money.amount shouldBe 1000
            }
        }
    }

    given("금액이 1000원이 주어졌을 때") {
        val money = Money(1000)
        When("반대값을 계산하면") {
            val result = money.opposite()
            then("반대값은 -1000원이다.") {
                result shouldBe Money(-1000)
            }
        }
    }
})
