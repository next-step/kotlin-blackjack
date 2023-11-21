package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row

class BettingMoneyTest : BehaviorSpec({
    Given("0 이하의 숫자나 1,000,000 초과의 숫자가 주어지면") {
        When("베팅 금액은") {
            Then("에러가 발생한다.") {
                forAll(
                    row(-1),
                    row(0),
                    row(1_000_001),
                ) { value ->
                    shouldThrow<IllegalArgumentException> {
                        BettingMoney(value)
                    }
                }
            }
        }
    }
})
