package next.step.blackjack.domain.betting

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class BettingResultsTest : DescribeSpec({

    describe("BettingResults") {
        context("플레이어 결과를 넘기면") {
            it("딜러 결과까지 계산") {
                BettingResults.from(mapOf("dj" to -20000, "dj2" to 10000)).dealerResult shouldBe 10000
            }
        }
    }
})
