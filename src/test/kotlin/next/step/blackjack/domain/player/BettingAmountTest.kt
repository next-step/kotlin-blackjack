package next.step.blackjack.domain.player

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import next.step.blackjack.domain.betting.BettingAmount
import next.step.blackjack.domain.game.GameOdds

class BettingAmountTest : DescribeSpec({

    describe("BettingAmount") {
        context("0보다 작은 금액으로 생성하면, 예외 발생") {
            withData(
                listOf(0, -1)
            ) { amount ->
                shouldThrow<IllegalArgumentException> { BettingAmount.of(amount) }
            }
        }

        context("GameOdds와 곱하면") {
            it("곱셈 결과를 Int로 변환하여 제공") {
                BettingAmount.of(10000) * GameOdds.BLACKJACK shouldBe 15000
            }
        }
    }

})
